package com.davada.application.supplier.persistence;

import com.davada.application.common.persistence.data.ContactData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.supplier.persistence.data.SupplierData;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.supplier.entity.Supplier;
import com.davada.domain.wholesaler.vo.Location;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface SupplierDataMapper {

    @Mapping(target = "version", ignore = true)
    SupplierData toSupplierData(Supplier Supplier);

    Supplier fromSupplierData(SupplierData supplierData);

    ContactData toContactData(Contact contact);

    LocationData toLocationData(Location location);
}
