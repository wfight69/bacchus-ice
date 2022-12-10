package com.davada.application.code.service;

import com.davada.domain.code.entity.ErpCodeSet;
import com.davada.dto.code.ErpCodeSetDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ErpCodeSetDtoMapper {
    ErpCodeSetDto toErpCodeSetDto(ErpCodeSet erpCodeSet);
}
