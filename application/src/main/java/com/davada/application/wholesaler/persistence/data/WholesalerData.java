package com.davada.application.wholesaler.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.common.persistence.data.ContactData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.wholesaler.persistence.WholesalerDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.util.JsonHelper;
import com.davada.domain.common.vo.CompanyType;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.common.vo.IndustryType;
import com.davada.domain.wholesaler.vo.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "wholesaler")
public class WholesalerData {
    @Id
    private String wholesalerUuid;
    private String wholesalerCode;
    private String wholesalerName;
    @Embedded
    private LocationData location;
    private String businessNumber;
    private String corporationNumber;
    private String uptae;
    private String jongmok;
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;
    @Enumerated(EnumType.STRING)
    private IndustryType industryType;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",
                    column = @Column(name = "wholesaler_sub_name")),
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
    private String serviceStartDate;
    private Integer serviceMonthlyAmount;

    // 서비스상태
    @Enumerated(EnumType.STRING)
    private WholesalerStatus wholesalerStatus;

    // 비고
    private String remarks;

    @Embedded
    private VanData van;

    @Embedded
    private ArsData ars;

    @Embedded
    private MobileData mobile;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, WholesalerDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("wholesalerCode", value -> this.wholesalerCode = trim(value));
            nameValuePairs.pullOut("wholesalerName", value -> this.wholesalerName = trim(value));
            nameValuePairs.pullOut("location", value -> this.location =
                    jpaMapper.toLocationData(JsonHelper.fromJson(value, Location.class)));
            nameValuePairs.pullOut("businessNumber", value -> this.businessNumber = trim(value));
            nameValuePairs.pullOut("corporationNumber", value -> this.corporationNumber = trim(value));
            nameValuePairs.pullOut("uptae", value -> this.uptae = trim(value));
            nameValuePairs.pullOut("jongmok", value -> this.jongmok = trim(value));
            nameValuePairs.pullOut("companyType", value -> this.companyType = CompanyType.valueOf(value));
            nameValuePairs.pullOut("industryType", value -> this.industryType = IndustryType.valueOf(value));
            nameValuePairs.pullOut("contact", value -> this.contact =
                    jpaMapper.toContactData(JsonHelper.fromJson(value, Contact.class)));
            nameValuePairs.pullOut("reprsContact", value -> this.reprsContact =
                    jpaMapper.toContactData(JsonHelper.fromJson(value, Contact.class)));
            nameValuePairs.pullOut("chargeContact", value -> this.chargeContact =
                    jpaMapper.toContactData(JsonHelper.fromJson(value, Contact.class)));
            nameValuePairs.pullOut("serviceStartDate", value -> this.serviceStartDate = trim(value));
            nameValuePairs.pullOut("serviceMonthlyAmount", value -> this.serviceMonthlyAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("wholesalerStatus", value -> this.wholesalerStatus = WholesalerStatus.valueOf(value));
            nameValuePairs.pullOut("remarks", value -> this.remarks = trim(value));
            nameValuePairs.pullOut("van", value -> this.van =
                    jpaMapper.toVanData(JsonHelper.fromJson(value, Van.class)));
            nameValuePairs.pullOut("ars", value -> this.ars =
                    jpaMapper.toArsData(JsonHelper.fromJson(value, Ars.class)));
            nameValuePairs.pullOut("mobile", value -> this.mobile =
                    jpaMapper.toMobileData(JsonHelper.fromJson(value, Mobile.class)));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
