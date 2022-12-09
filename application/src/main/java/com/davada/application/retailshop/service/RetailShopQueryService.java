package com.davada.application.retailshop.service;

import com.davada.application.employee.service.port.EmployeeCrudUseCase;
import com.davada.application.retailshop.persistence.RetailShopPersistenceAdapter;
import com.davada.application.retailshop.service.port.RetailShopQueryUseCase;
import com.davada.domain.common.vo.EmployeeManager;
import com.davada.domain.retailshop.condition.RetailShopCondition;
import com.davada.dto.retailshop.RetailShopDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isNotEmpty;

@ApplicationScoped
@RequiredArgsConstructor
public class RetailShopQueryService implements RetailShopQueryUseCase {
    private final RetailShopPersistenceAdapter retailShopQueryPort;

    public final EmployeeCrudUseCase employeeCrudUseCase;
    private final RetailShopDtoMapper retailShopDtoMapper;

    @Override
    public List<RetailShopDto> retrieveListByCondition(RetailShopCondition condition, int offset, int limit) {
        return retailShopQueryPort.retrieveListByCondition(condition, offset, limit)
                .stream()
                .map(retailShop -> {
                    EmployeeManager retailSalesPerson = null;
                    if (isNotEmpty(retailShop.getEmployeeUuid())) {
                        EmployeeSimpleDto employeeDto = employeeCrudUseCase.retrieveEmployeeSimpleDto(retailShop.getEmployeeUuid());
                        retailSalesPerson = retailShopDtoMapper.toEmployeeManager(employeeDto);
                    }
                    return retailShopDtoMapper.toRetailShopDto(retailShop, retailSalesPerson);
                })
                .collect(Collectors.toList());
    }

    @Override
    public long countByCondition(RetailShopCondition condition) {
        return retailShopQueryPort.countByCondition(condition);
    }
}
