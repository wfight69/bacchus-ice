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
    default RetailMaintenance toDomain(String orderUuid, RetailRequestMaintenanceCrudUseCase.CreateRequestMaintenanceCommand command) {
        RetailMaintenance retailMaintenance = RetailMaintenance.getInstance();
        retailMaintenance.setMaintenanceUuid(orderUuid);
        retailMaintenance.setRetailMaintenanceStatus(RetailMaintenanceStatus.RECEIVED);
        retailMaintenance.setWholesalerUuid(command.getWholesalerUuid());
        retailMaintenance.setRetailMaintenanceChannel(command.getRetailMaintenanceChannel());
        //retailMaintenance.setMaintenanceDescription(command.getMaintenanceDescription());
        retailMaintenance.setMaintenanceDate(DateHelper.currentDateString());
        retailMaintenance.setMaintenanceTime(DateHelper.currentTimeString());

//        if (retailShopDto != null) {
//            retailMaintenance.setRetailShopUuid(retailShopDto.getRetailShopUuid());
//            retailMaintenance.setRetailShopCode(retailShopDto.getRetailShopCode());
//            retailMaintenance.setRetailShopName(retailShopDto.getRetailShopName());
//            if (retailShopDto.getSalesManager() != null) {
//                retailMaintenance.setEmployeeUuid(retailShopDto.getSalesManager().getEmployeeUuid());
//                retailMaintenance.setEmployeeName(retailShopDto.getSalesManager().getEmployeeName());
//                retailMaintenance.setSalesCourse(retailShopDto.getSalesManager().getSalesCourse());
//            }
//        }

        return retailMaintenance;
    }

}
