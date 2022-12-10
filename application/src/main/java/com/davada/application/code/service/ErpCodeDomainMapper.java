package com.davada.application.code.service;

import com.davada.application.code.service.port.ErpCodeCrudUseCase;
import com.davada.domain.code.entity.ErpCode;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ErpCodeDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    @Mapping(target = "codeUuid", source = "codeUuid")
    ErpCode toDomain(String codeUuid, ErpCodeCrudUseCase.CreateErpCodeCommand command);
    @Mapping(target = "auditLog", ignore = true)
    ErpCode toDomain(ErpCodeCrudUseCase.CreateErpCodeCommand command);
}
