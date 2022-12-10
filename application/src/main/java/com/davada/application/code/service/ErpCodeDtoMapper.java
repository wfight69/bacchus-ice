package com.davada.application.code.service;

import com.davada.domain.code.entity.ErpCode;
import com.davada.dto.code.ErpCodeDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ErpCodeDtoMapper {
    ErpCodeDto toErpCodeDto(ErpCode erpCode);
}
