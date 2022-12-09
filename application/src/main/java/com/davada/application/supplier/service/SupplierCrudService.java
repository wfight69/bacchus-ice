package com.davada.application.supplier.service;

import com.davada.application.employee.service.port.EmployeeCrudUseCase;
import com.davada.application.supplier.persistence.SupplierPersistenceAdapter;
import com.davada.application.supplier.service.port.SupplierCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.EmployeeManager;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.supplier.entity.Supplier;
import com.davada.domain.supplier.error.SupplierErrorCodes;
import com.davada.dto.supplier.SupplierDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.common.util.StringHelper.isNotEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class SupplierCrudService implements SupplierCrudUseCase {

    public final SupplierPersistenceAdapter supplierCrudPort;
    public final EmployeeCrudUseCase retrieveEmployeePort;

    private final SupplierDomainMapper supplierDomainMapper;
    private final SupplierDtoMapper supplierDtoMapper;

    @Override
    public IdValue createSupplier(Supplier supplier) {
        String supplierUuid = ErpId.newId().getUuid().toString();
//        Supplier supplier = supplierDomainMapper.toDomain(supplierUuid, command);
//        supplier.refineValues();
        supplier.setSupplierUuid(supplierUuid);
        //
        if (isNotEmpty(supplier.getEmployeeUuid())) {
            // Employee 지정되었을대 존재하는지 체크한다, 없으면 예외 발생
            retrieveEmployeePort.retrieveEmployee(supplier.getEmployeeUuid());
        }
        supplierCrudPort.create(supplier);
        return new IdValue("supplierUuid", supplierUuid);
    }

    @Override
    public SupplierDto retrieveSupplier(String supplierUuid) {
        Supplier supplier = supplierCrudPort.retrieve(supplierUuid)
                .orElseThrow(() -> new ErpRuntimeException(SupplierErrorCodes.SUPPLIER_1000, supplierUuid));

        EmployeeManager purchaseManager = null;
        if (isNotEmpty(supplier.getEmployeeUuid())) {
            EmployeeSimpleDto employeeDto = retrieveEmployeePort.retrieveEmployeeSimpleDto(supplier.getEmployeeUuid());
            purchaseManager = supplierDtoMapper.toEmployeeManager(employeeDto);
        }
        return supplierDtoMapper.toSupplierDto(supplier, purchaseManager);
    }

    @Override
    public List<SupplierDto> retrieveAllSupplier(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return supplierCrudPort.retrieveAllSupplier(wholesalerUuid)
                .stream()
                .map(supplier -> {
                    EmployeeManager purchaseManager = null;
                    if (isNotEmpty(supplier.getEmployeeUuid())) {
                        EmployeeSimpleDto employeeDto = retrieveEmployeePort.retrieveEmployeeSimpleDto(supplier.getEmployeeUuid());
                        purchaseManager = supplierDtoMapper.toEmployeeManager(employeeDto);
                    }
                    return supplierDtoMapper.toSupplierDto(supplier, purchaseManager);
                })
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateSupplier(String supplierUuid, NameValuePairs nameValuePairs) {
        return supplierCrudPort.retrieve(supplierUuid).map(supplier -> {
            boolean modified = supplierCrudPort.update(supplierUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new SupplierRemovedEvent(supplier, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(supplierUuid));
    }

    @Override
    public BooleanValue deleteSupplier(String supplierUuid, boolean permanent) {
        return supplierCrudPort.retrieve(supplierUuid).map(supplier -> {
            boolean removed = supplierCrudPort.delete(supplier, permanent);
            if (removed) {
                // domainEventPublisher.publish(new SupplierRemovedEvent(supplier, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(supplierUuid));
    }
}
