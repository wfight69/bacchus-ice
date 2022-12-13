package com.davada.maintenance;

import com.davada.domain.maintenance.condition.RetailMaintenanceCondition;
import com.davada.domain.maintenance.condition.RetailRequestMaintenanceCondition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ParameterBeanMapper {

    RetailMaintenanceCondition toCondition(String wholesalerUuid, RetailMaintenanceQueryController.ParameterBean parameterBean);

    RetailRequestMaintenanceCondition toCondition(String wholesalerUuid, RetailRequestMaintenanceQueryController.ParameterBean parameterBean);

    //PurchaseMaintenanceCondition toCondition(String wholesalerUuid, PurchaseMaintenanceQueryController.ParameterBean parameterBean);
}
