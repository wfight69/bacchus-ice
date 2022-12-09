package com.davada.application.icesaler.service;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface IcesalerDomainMapper {

    //@Mapping(target = "auditLog", ignore = true)
    //Wholesaler toDomain(String wholesalerUuid, WholesalerCrudUseCase.CreateWholesalerCommand command);
}
