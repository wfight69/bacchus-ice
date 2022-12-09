package com.davada.retailshop;

import com.davada.domain.retailshop.condition.RetailShopCondition;
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
    RetailShopCondition toRetailShopCondition(String wholesalerUuid, RetailShopQueryController.ParameterBean parameterBean);
}
