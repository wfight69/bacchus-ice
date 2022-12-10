package com.davada.application.code.persistence;

import com.davada.application.code.persistence.data.ErpCodeData;
import com.davada.domain.code.entity.ErpCode;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ErpCodeDataMapper {
    @Mapping(target = "version", ignore = true)
    ErpCodeData toErpCodeData(ErpCode erpCode);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "codeUuid", source = "codeUuid")
    ErpCodeData toErpCodeData(String codeUuid, ErpCode erpCode);

    ErpCode fromErpCodeData(ErpCodeData erpCodeData);
}
