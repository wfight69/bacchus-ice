package com.davada.application.maintenance.service;

import com.davada.application.maintenance.service.port.RetailRequestMaintenanceCrudUseCase;
import com.davada.domain.common.util.DateHelper;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import com.davada.domain.maintenance.vo.RetailMaintenanceStatus;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailRequestMaintenanceDomainMapper {
    default RetailMaintenance toDomain(String maintenanceUuid, RetailRequestMaintenanceCrudUseCase.CreateRequestMaintenanceCommand command) {
        RetailMaintenance retailMaintenance = RetailMaintenance.getInstance();
        retailMaintenance.setMaintenanceUuid(maintenanceUuid);
        retailMaintenance.setIcesalerUuid(command.getIcesalerUuid());
        retailMaintenance.setWholesalerUuid(command.getWholesalerUuid());
        retailMaintenance.setRetailShopUuid(command.getRetailShopUuid());
        retailMaintenance.setRetailShopCode(command.getRetailShopCode());
        retailMaintenance.setRetailShopName(command.getRetailShopName());
        retailMaintenance.setRetailMaintenanceStatus(RetailMaintenanceStatus.RECEIVED);
        retailMaintenance.setMaintenanceType(command.getMaintenanceType());
        retailMaintenance.setRetailMaintenanceChannel(command.getRetailMaintenanceChannel());
        retailMaintenance.setCreateDescription(command.getCreateDescription());
        retailMaintenance.setMaintenanceDate(DateHelper.currentDateString());
        retailMaintenance.setMaintenanceTime(DateHelper.currentTimeString());

        return retailMaintenance;
    }

}
