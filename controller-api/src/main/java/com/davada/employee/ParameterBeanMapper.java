package com.davada.employee;

import com.davada.domain.wholesaler.condition.EmployeeCondition;
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
    EmployeeCondition toEmployeeCondition(String wholesalerUuid, EmployeeQueryController.ParameterBean parameterBean);
}
