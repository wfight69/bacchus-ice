package com.davada.application.retailshop.persistence;

import com.davada.application.common.persistence.data.ContactData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.retailshop.persistence.data.ChargeDetailsData;
import com.davada.application.retailshop.persistence.data.RetailShopData;
import com.davada.application.retailshop.persistence.data.ServicePeriodData;
import com.davada.domain.common.entity.ChargeDetails;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.retailshop.entity.RetailShop;
import com.davada.domain.retailshop.vo.ServicePeriod;
import com.davada.domain.wholesaler.vo.Location;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetailShopDataMapper {
    @Mapping(target = "version", ignore = true)
    RetailShopData toRetailShopData(RetailShop retailShop);

    @InheritInverseConfiguration
    RetailShop fromRetailShopData(RetailShopData retailShopData);

    ContactData toContactData(Contact contact);

    LocationData toLocationData(Location location);

    ChargeDetailsData toChargeDetailsData(ChargeDetails chargeDetails);

    ServicePeriodData toServicePeriodData(ServicePeriod servicePeriod);
}
