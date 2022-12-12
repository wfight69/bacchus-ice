package com.davada.application.maintenance.service;

import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import com.davada.dto.maintenance.RetailMaintenanceItemDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailMaintenanceItemDtoMapper {
    RetailMaintenanceItemDto toRetailMaintenanceItemDto(RetailMaintenanceItem retailMaintenanceItem);
}
