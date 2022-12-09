package com.davada.application.retailshop.service;

import com.davada.application.employee.service.port.EmployeeCrudUseCase;
import com.davada.application.retailshop.persistence.RetailOrderTelephonePersistenceAdapter;
import com.davada.application.retailshop.persistence.RetailShopPersistenceAdapter;
import com.davada.application.retailshop.service.port.RetailShopCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.EmployeeManager;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.retailshop.entity.RetailShop;
import com.davada.domain.retailshop.error.RetailShopErrorCodes;
import com.davada.dto.retailshop.RetailShopDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.common.util.StringHelper.isNotEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailShopCrudService implements RetailShopCrudUseCase {

    private final RetailShopPersistenceAdapter retailShopCrudPort;
    private final EmployeeCrudUseCase retrieveEmployeePort;
    private final RetailOrderTelephonePersistenceAdapter retailOrderTelephonePort;
    private final RetailShopDomainMapper retailShopDomainMapper;
    private final RetailShopDtoMapper retailShopDtoMapper;

//    @Override
//    public IdValue createRetailShop(RetailShopCrudUseCase.CreateRetailShopCommand command) {
//        String retailShopUuid = ErpId.newId().getUuid().toString();
//        RetailShop retailShop = retailShopDomainMapper.toDomain(retailShopUuid, command);
//        retailShop.refineValues();
//
//        if (isNotEmpty(retailShop.getEmployeeUuid())) {
//            // Employee 지정되었을대 존재하는지 체크한다, 없으면 예외 발생
//            retrieveEmployeePort.retrieveEmployee(retailShop.getEmployeeUuid());
//        }
//
//        retailShopCrudPort.create(retailShop);
//        return new IdValue("retailShopUuid", retailShopUuid);
//    }
    @Override
    public IdValue createRetailShop(RetailShop retailShop) {
        String retailShopUuid = ErpId.newId().getUuid().toString();
        retailShop.setRetailShopUuid(retailShopUuid);
        //
        if (isNotEmpty(retailShop.getEmployeeUuid())) {
            // Employee 지정되었을대 존재하는지 체크한다, 없으면 예외 발생
            retrieveEmployeePort.retrieveEmployee(retailShop.getEmployeeUuid());
        }
        retailShopCrudPort.create(retailShop);
        return new IdValue("retailShopUuid", retailShopUuid);
    }

    @Override
    public RetailShopDto retrieveRetailShop(String retailShopUuid) {
        RetailShop retailShop = retailShopCrudPort.retrieve(retailShopUuid)
                .orElseThrow(() -> new ErpRuntimeException(RetailShopErrorCodes.RETAIL_SHOP_1000, retailShopUuid));

        EmployeeManager salesManager = null;
        if (isNotEmpty(retailShop.getEmployeeUuid())) {
            EmployeeSimpleDto employeeDto = retrieveEmployeePort.retrieveEmployeeSimpleDto(retailShop.getEmployeeUuid());
            salesManager = retailShopDtoMapper.toEmployeeManager(employeeDto);
        }

        return retailShopDtoMapper.toRetailShopDto(retailShop, salesManager);
    }

    @Override
    public List<RetailShopDto> retrieveAllRetailShop(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return retailShopCrudPort.retrieveAllRetailShop(wholesalerUuid)
                .stream()
                .map(retailShop -> {
                    EmployeeManager retailSalesPerson = null;
                    if (isNotEmpty(retailShop.getEmployeeUuid())) {
                        EmployeeSimpleDto employeeDto = retrieveEmployeePort.retrieveEmployeeSimpleDto(retailShop.getEmployeeUuid());
                        retailSalesPerson = retailShopDtoMapper.toEmployeeManager(employeeDto);
                    }
                    return retailShopDtoMapper.toRetailShopDto(retailShop, retailSalesPerson);
                })
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateRetailShop(String retailShopUuid, NameValuePairs nameValuePairs) {
        return retailShopCrudPort.retrieve(retailShopUuid).map(retailShop -> {
            boolean modified = retailShopCrudPort.update(retailShopUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new RetailShopRemovedEvent(retailShop, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(retailShopUuid));
    }

    @Override
    public BooleanValue deleteRetailShop(String retailShopUuid, boolean permanent) {
        return retailShopCrudPort.retrieve(retailShopUuid).map(retailShop -> {
            boolean removed = retailShopCrudPort.delete(retailShop, permanent);
            if (removed) {
                // domainEventPublisher.publish(new RetailShopRemovedEvent(retailShop, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(retailShopUuid));
    }

    @Override
    public Optional<RetailShopDto> findByRetailOrderTelephone(String telephone) {
        return retailOrderTelephonePort.findByTelephone(telephone)
                .map(value -> retrieveRetailShop(value.getRetailShopUuid()));
    }

}
