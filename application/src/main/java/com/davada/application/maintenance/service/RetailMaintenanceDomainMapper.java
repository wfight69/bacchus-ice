package com.davada.application.maintenance.service;

import com.davada.application.maintenance.service.port.RetailMaintenanceCrudUseCase;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailMaintenanceDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    @Mapping(target = "amount", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "vat", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "subtotalAmount", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "totalAmount", expression = "java(BigDecimal.ZERO)")
    RetailMaintenance toDomain(String maintenanceUuid, RetailMaintenanceCrudUseCase.CreateMaintenanceCommand command);
}
