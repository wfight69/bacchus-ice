package com.davada.application.maintenance.service;

import com.davada.application.maintenance.service.port.RetailMaintenanceItemCrudUseCase;
import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailMaintenanceItemDomainMapper {
    RetailMaintenanceItem toDomain(String orderItemUuid, RetailMaintenanceItemCrudUseCase.CreateMaintenanceItemCommand command);
}
