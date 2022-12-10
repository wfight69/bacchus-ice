package com.davada.application.code.service;

import com.davada.application.code.service.port.ErpCodeSetCrudUseCase;
import com.davada.domain.code.entity.ErpCodeSet;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ErpCodeSetDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    ErpCodeSet toDomain(String codeSetUuid, ErpCodeSetCrudUseCase.CreateErpCodeSetCommand command);
}
