package com.davada.application.icesaler.persistence;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.common.persistence.data.ContactData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.icesaler.persistence.data.IcesalerData;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.icesaler.entity.Icesaler;
import com.davada.domain.wholesaler.vo.Location;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface IcesalerDataMapper {

    @Mapping(target = "version", ignore = true)
    IcesalerData toIcesalerData(Icesaler icesaler);

    @InheritInverseConfiguration
    Icesaler fromIcesalerData(IcesalerData icesalerData);

    //    @Mapping(target = "applyUpdateAuditLog", ignore = true)
//    @Mapping(target = "applyDeleteAuditLog", ignore = true)
    AuditLog fromAuditLogData(AuditLogData auditLogData);

    ContactData toContactData(Contact contact);

    LocationData toLocationData(Location location);

    @InheritInverseConfiguration
    AuditLogData toAuditLogData(AuditLog auditLog);
}
