package com.davada.application.maintenance.persistence;

import com.davada.application.maintenance.persistence.data.RetailMaintenanceItemData;
import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailMaintenanceItemDataMapper {

    @Mapping(target = "maintenance", ignore = true)
    RetailMaintenanceItemData toMaintenanceItemData(RetailMaintenanceItem maintenanceItem);

    RetailMaintenanceItem fromMaintenanceItemData(RetailMaintenanceItemData retailMaintenanceItemData);
}
