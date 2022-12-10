package com.davada.application.code.persistence;

import com.davada.application.code.persistence.data.ErpCodeSetData;
import com.davada.domain.code.entity.ErpCodeSet;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ErpCodeSetDataMapper {
    @Mapping(target = "version", ignore = true)
    ErpCodeSetData toErpCodeSetData(ErpCodeSet erpCodeSet);
    ErpCodeSet fromErpCodeSetData(ErpCodeSetData erpCodeSetData);
}
