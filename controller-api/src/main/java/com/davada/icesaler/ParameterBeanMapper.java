package com.davada.icesaler;

import com.davada.domain.icesaler.condition.IcesalerCondition;
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

    IcesalerCondition toIcesalerCondition(IcesalerQueryController.ParameterBean parameterBean);
}
