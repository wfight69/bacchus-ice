package com.davada.code;

import com.davada.domain.code.condition.ErpCodeCondition;
import com.davada.domain.code.condition.ErpCodeSetCondition;
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
    ErpCodeCondition toErpCodeCondition(String wholesalerUuid, ErpCodeQueryController.ParameterBean parameterBean);

    ErpCodeSetCondition toErpCodeSetCondition(String wholesalerUuid, ErpCodeSetQueryController.ParameterBean parameterBean);
}
