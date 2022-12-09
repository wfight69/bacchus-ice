package com.davada.application.wholesaler.service;

import com.davada.application.wholesaler.service.port.WholesalerCrudUseCase;
import com.davada.domain.wholesaler.entity.Wholesaler;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface WholesalerDomainMapper {

    @Mapping(target = "auditLog", ignore = true)
    Wholesaler toDomain(String wholesalerUuid, WholesalerCrudUseCase.CreateWholesalerCommand command);
}
