package com.davada.application.retailshop.persistence;

import com.davada.application.retailshop.persistence.data.RetailOrderTelephoneData;
import com.davada.domain.retailshop.entity.RetailOrderTelephone;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailOrderTelephoneDataMapper {
    @Mapping(target = "version", ignore = true)
    RetailOrderTelephoneData toRetailOrderTelephoneData(RetailOrderTelephone retailShop);

    @InheritInverseConfiguration
    RetailOrderTelephone fromRetailOrderTelephoneData(RetailOrderTelephoneData retailShopData);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "retailOrderTelephoneUuid", source = "retailOrderTelephoneUuid")
    @Mapping(target = "retailShopUuid", source = "retailShopUuid")
    RetailOrderTelephoneData toRetailOrderTelephoneData(String retailOrderTelephoneUuid, String retailShopUuid, RetailOrderTelephone retailOrderTelephone);

}
