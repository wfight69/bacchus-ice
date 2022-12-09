package com.davada.application.retailshop.service;

import com.davada.application.retailshop.service.port.RetailShopCrudUseCase;
import com.davada.domain.retailshop.entity.RetailShop;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailShopDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    RetailShop toDomain(String retailShopUuid, RetailShopCrudUseCase.CreateRetailShopCommand command);
}
