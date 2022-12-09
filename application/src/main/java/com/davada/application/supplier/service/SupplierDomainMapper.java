package com.davada.application.supplier.service;

import com.davada.application.supplier.service.port.SupplierCrudUseCase;
import com.davada.domain.supplier.entity.Supplier;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface SupplierDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    Supplier toDomain(String supplierUuid, SupplierCrudUseCase.CreateSupplierCommand command);
}
