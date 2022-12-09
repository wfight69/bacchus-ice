package com.davada.application.retailshop.persistence.data;

import com.davada.application.common.persistence.data.ContactData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.retailshop.persistence.RetailShopDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.entity.ChargeDetails;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.util.JsonHelper;
import com.davada.domain.common.vo.*;
import com.davada.domain.retailshop.vo.ServicePeriod;
import com.davada.domain.wholesaler.vo.Location;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "retail_shop")
public class RetailShopData {
    @Id
    private String retailShopUuid;
    private String wholesalerUuid;
    private String retailShopCode;
    private String retailShopName;
    private String businessNumber;
    //private String representativeName;
    private String mobilePhoneNumber;
    @Embedded
    private LocationData location;
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;
    @Enumerated(EnumType.STRING)
    private IndustryType industryType;
    private Long bondLimitAmount;
    // Long currentBondAmount;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",
                    column = @Column(name = "retail_shop_sub_name")),
            @AttributeOverride(name = "telephone",
                    column = @Column(name = "retail_shop_telephone")),
            @AttributeOverride(name = "fax",
                    column = @Column(name = "retail_shop_fax")),
            @AttributeOverride(name = "email",
                    column = @Column(name = "retail_shop_email")),
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
    private String licenseNo;
    private String uptae;
    private String jongmok;
    private String employeeUuid; // A sales manager from Wholesaler.Employee
    @Enumerated(EnumType.STRING)
    private BusinessCategory businessCategory;
    @Enumerated(EnumType.STRING)
    private YN containerDeposit;
    @Enumerated(EnumType.STRING)
    private ShopStatus retailShopStatus;
    @Embedded
    private ServicePeriodData servicePeriod;
    private String description;
    @Embedded
    private ChargeDetailsData chargeDetails;
    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, RetailShopDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("retailShopName",
                    value -> this.retailShopName = trim(value));
            nameValuePairs.pullOut("businessNumber",
                    value -> this.businessNumber = trim(value));
//            nameValuePairs.pullOut("representativeName",
//                    value -> this.representativeName = trim(value));
            nameValuePairs.pullOut("mobilePhoneNumber",
                    value -> this.mobilePhoneNumber = trim(value));
            nameValuePairs.pullOut("location",
                    value -> this.location = jpaMapper.toLocationData(fromJson(value, Location.class)));
            nameValuePairs.pullOut("companyType",
                    value -> this.companyType = CompanyType.valueOf(value));
            nameValuePairs.pullOut("industryType",
                    value -> this.industryType = IndustryType.valueOf(value));
            nameValuePairs.pullOut("bondLimitAmount",
                    value -> this.bondLimitAmount = Long.valueOf(value));
            nameValuePairs.pullOut("contact",
                    value -> this.contact = jpaMapper.toContactData(fromJson(value, Contact.class)));
            nameValuePairs.pullOut("reprsContact", value -> this.reprsContact =
                    jpaMapper.toContactData(JsonHelper.fromJson(value, Contact.class)));
            nameValuePairs.pullOut("chargeContact", value -> this.chargeContact =
                    jpaMapper.toContactData(JsonHelper.fromJson(value, Contact.class)));
            nameValuePairs.pullOut("licenseNo",
                    value -> this.licenseNo = trim(value));
            nameValuePairs.pullOut("uptae",
                    value -> this.uptae = trim(value));
            nameValuePairs.pullOut("jongmok",
                    value -> this.jongmok = trim(value));
            nameValuePairs.pullOut("employeeUuid",
                    value -> this.employeeUuid = trim(value));
            nameValuePairs.pullOut("businessCategory",
                    value -> this.businessCategory = BusinessCategory.valueOf(value));
            nameValuePairs.pullOut("containerDeposit",
                    value -> this.containerDeposit = YN.valueOf(value));
            nameValuePairs.pullOut("retailShopStatus",
                    value -> this.retailShopStatus = ShopStatus.valueOf(value));
            nameValuePairs.pullOut("servicePeriod",
                    value -> {
                        ServicePeriod servicePeriodDomain = fromJson(value, ServicePeriod.class);
                        servicePeriodDomain.refineValues();
                        this.servicePeriod = jpaMapper.toServicePeriodData(servicePeriodDomain);
                    });
            nameValuePairs.pullOut("description",
                    value -> this.description = trim(value));
            nameValuePairs.pullOut("chargeDetails",
                    value -> {
                        ChargeDetails chargeDetailsDomain = fromJson(value, ChargeDetails.class);
                        chargeDetailsDomain.refineValues();
                        this.chargeDetails = jpaMapper.toChargeDetailsData(chargeDetailsDomain);
                    });

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
