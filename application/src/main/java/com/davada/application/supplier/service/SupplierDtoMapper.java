package com.davada.application.supplier.service;

import com.davada.domain.common.vo.EmployeeManager;
import com.davada.domain.supplier.entity.Supplier;
import com.davada.dto.supplier.SupplierDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface SupplierDtoMapper {
    EmployeeManager toEmployeeManager(EmployeeSimpleDto employeeDto);

    @Mapping(target = "description", source = "supplier.description")
    SupplierDto toSupplierDto(Supplier supplier, EmployeeManager purchaseManager);
}
