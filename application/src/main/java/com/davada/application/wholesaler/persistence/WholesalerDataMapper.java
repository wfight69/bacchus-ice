package com.davada.application.wholesaler.persistence;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.common.persistence.data.ContactData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.wholesaler.persistence.data.*;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.wholesaler.entity.Wholesaler;
import com.davada.domain.wholesaler.vo.Ars;
import com.davada.domain.wholesaler.vo.Location;
import com.davada.domain.wholesaler.vo.Mobile;
import com.davada.domain.wholesaler.vo.Van;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface WholesalerDataMapper {

    @Mapping(target = "version", ignore = true)
    WholesalerData toWholesalerData(Wholesaler wholesaler);

    @InheritInverseConfiguration
    Wholesaler fromWholesalerData(WholesalerData wholesalerData);

    //    @Mapping(target = "applyUpdateAuditLog", ignore = true)
//    @Mapping(target = "applyDeleteAuditLog", ignore = true)
    AuditLog fromAuditLogData(AuditLogData auditLogData);

    ContactData toContactData(Contact contact);

    LocationData toLocationData(Location location);

    VanData toVanData(Van van);

    ArsData toArsData(Ars ars);

    MobileData toMobileData(Mobile mobile);

    @InheritInverseConfiguration
    AuditLogData toAuditLogData(AuditLog auditLog);
}
