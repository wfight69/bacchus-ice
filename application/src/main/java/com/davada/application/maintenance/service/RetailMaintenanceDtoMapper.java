package com.davada.application.maintenance.service;

import com.davada.domain.maintenance.entity.RetailMaintenance;
import com.davada.dto.maintenance.RetailMaintenanceDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailMaintenanceDtoMapper {
    RetailMaintenanceDto toRetailMaintenanceDto(RetailMaintenance retailMaintenance);
}
