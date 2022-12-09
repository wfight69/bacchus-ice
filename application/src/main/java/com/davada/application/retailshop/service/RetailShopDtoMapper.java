package com.davada.application.retailshop.service;

import com.davada.domain.common.vo.EmployeeManager;
import com.davada.domain.retailshop.entity.RetailOrderTelephone;
import com.davada.domain.retailshop.entity.RetailShop;
import com.davada.dto.retailshop.RetailOrderTelephoneDto;
import com.davada.dto.retailshop.RetailShopDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailShopDtoMapper {
    EmployeeManager toEmployeeManager(EmployeeSimpleDto employeeDto);

    @Mapping(target = "description", source = "retailShop.description")
    RetailShopDto toRetailShopDto(RetailShop retailShop, EmployeeManager salesManager);

    @Mapping(target = "auditLog", ignore = true)
    RetailOrderTelephone toRetailOrderTelephone(RetailOrderTelephoneDto retailOrderTelephoneDto);
}
