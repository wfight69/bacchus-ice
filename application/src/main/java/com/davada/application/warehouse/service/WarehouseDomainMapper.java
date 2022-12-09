package com.davada.application.warehouse.service;

import com.davada.application.warehouse.service.port.WarehouseCrudUseCase;
import com.davada.domain.product.entity.Warehouse;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface WarehouseDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    Warehouse toDomain(String warehouseUuid, WarehouseCrudUseCase.CreateWarehouseCommand command);
}
