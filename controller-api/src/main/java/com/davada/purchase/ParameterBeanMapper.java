package com.davada.purchase;

import com.davada.domain.purchase.condition.PurchaseOrderCondition;
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

    PurchaseOrderCondition toCondition(String wholesalerUuid, PurchaseOrderQueryController.ParameterBean parameterBean);
}
