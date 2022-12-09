package com.davada.application.warehouse.service;

import com.davada.domain.product.entity.Warehouse;
import com.davada.dto.product.WarehouseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface WarehouseDtoMapper {
    WarehouseDto toWarehouseDto(Warehouse warehouse);
}
