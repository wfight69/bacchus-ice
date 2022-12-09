package com.davada.application.supplier.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.common.persistence.data.ContactData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.supplier.persistence.SupplierDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.util.JsonHelper;
import com.davada.domain.common.vo.*;
import com.davada.domain.wholesaler.vo.Location;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.trim;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Cacheable
@Getter
@Table(name = "supplier")
public class SupplierData {
    @Id
    String supplierUuid;
    String wholesalerUuid;
    String supplierCode;
    String supplierName;
    String businessNumber;
    String mobilePhoneNumber;
    @Embedded
    LocationData location;
    @Enumerated(EnumType.STRING)
    CompanyType companyType;
    @Enumerated(EnumType.STRING)
    IndustryType industryType;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",
                    column = @Column(name = "supply_sub_name")),
            @AttributeOverride(name = "telephone",
                    column = @Column(name = "supply_telephone")),
            @AttributeOverride(name = "fax",
                    column = @Column(name = "supply_fax")),
            @AttributeOverride(name = "email",
                    column = @Column(name = "supply_email")),
    })
    private ContactData contact; // FIXME: companyContact

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",
                    column = @Column(name = "reprs_name")),
            @AttributeOverride(name = "telephone",
                    column = @Column(name = "reprs_telephone")),
            @AttributeOverride(name = "fax",
                    column = @Column(name = "reprs_fax")),
            @AttributeOverride(name = "email",
                    column = @Column(name = "reprs_email")),
    })
    private ContactData reprsContact; // FIXME: ceoContact

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",
                    column = @Column(name = "charge_name")),
            @AttributeOverride(name = "telephone",
                    column = @Column(name = "charge_telephone")),
            @AttributeOverride(name = "fax",
                    column = @Column(name = "charge_fax")),
            @AttributeOverride(name = "email",
                    column = @Column(name = "charge_email")),
    })
    private ContactData chargeContact; // FIXME: managerContact
    String uptae;
    String jongmok;

    String employeeUuid; // A purchasing manager from Wholesaler.Employee
    @Enumerated(EnumType.STRING)
    YN substitutionYn;
    @Enumerated(EnumType.STRING)
    ShopStatus supplierStatus;
    String description;
    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, SupplierDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("supplierCode",
                    value -> this.supplierCode = trim(value));
            nameValuePairs.pullOut("supplierName",
                    value -> this.supplierName = trim(value));
            nameValuePairs.pullOut("businessNumber",
                    value -> this.businessNumber = trim(value));
            nameValuePairs.pullOut("mobilePhoneNumber",
                    value -> this.mobilePhoneNumber = trim(value));
            nameValuePairs.pullOut("location",
                    value -> this.location = jpaMapper.toLocationData(fromJson(value, Location.class)));
            nameValuePairs.pullOut("companyType",
                    value -> this.companyType = CompanyType.valueOf(value));
            nameValuePairs.pullOut("industryType",
                    value -> this.industryType = IndustryType.valueOf(value));
            nameValuePairs.pullOut("contact",
                    value -> this.contact = jpaMapper.toContactData(fromJson(value, Contact.class)));
            nameValuePairs.pullOut("reprsContact", value -> this.reprsContact =
                    jpaMapper.toContactData(JsonHelper.fromJson(value, Contact.class)));
            nameValuePairs.pullOut("chargeContact", value -> this.chargeContact =
                    jpaMapper.toContactData(JsonHelper.fromJson(value, Contact.class)));
            nameValuePairs.pullOut("uptae",
                    value -> this.uptae = trim(value));
            nameValuePairs.pullOut("jongmok",
                    value -> this.jongmok = trim(value));
            nameValuePairs.pullOut("employeeUuid",
                    value -> this.employeeUuid = trim(value));
            nameValuePairs.pullOut("substitutionYn",
                    value -> this.substitutionYn = YN.valueOf(value));
            nameValuePairs.pullOut("supplierStatus",
                    value -> this.supplierStatus = ShopStatus.valueOf(value));
            nameValuePairs.pullOut("description",
                    value -> this.description = trim(value));
            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
