package com.davada.board;

import com.davada.domain.board.condition.NoticeBoardCondition;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ParameterBeanMapper {

    @Mapping(target = "views", ignore = true)
    NoticeBoardCondition toCondition(String wholesalerUuid, NoticeBoardQueryController.ParameterBean parameterBean);
}
