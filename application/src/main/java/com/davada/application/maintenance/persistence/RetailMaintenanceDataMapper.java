package com.davada.application.maintenance.persistence;

import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import com.davada.application.maintenance.persistence.data.RetailMaintenanceItemData;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailMaintenanceDataMapper {
    @Mapping(target = "version", ignore = true)
    RetailMaintenanceData toMaintenanceData(RetailMaintenance maintenance);

    RetailMaintenance fromMaintenanceData(RetailMaintenanceData retailMaintenanceData);

    @Mapping(target = "maintenance", ignore = true)
    RetailMaintenanceItemData toMaintenanceItemData(RetailMaintenanceItem maintenanceItem);
}
