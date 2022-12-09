package com.davada.application.retailshop.service;

import com.davada.application.retailshop.service.port.RetailOrderTelephoneUseCase;
import com.davada.domain.retailshop.entity.RetailOrderTelephone;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailOrderTelephoneDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    RetailOrderTelephone toDomain(String retailOrderTelephoneUuid, RetailOrderTelephoneUseCase.CreateRetailOrderTelephoneCommand command);
}
