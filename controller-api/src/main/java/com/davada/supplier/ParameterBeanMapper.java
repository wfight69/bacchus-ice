package com.davada.supplier;

import com.davada.domain.supplier.condition.SupplierCondition;
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
    SupplierCondition toSupplierCondition(String wholesalerUuid, SupplierQueryController.ParameterBean parameterBean);
}
