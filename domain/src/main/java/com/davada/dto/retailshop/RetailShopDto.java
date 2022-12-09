package com.davada.dto.retailshop;

import com.davada.domain.common.entity.ChargeDetails;
import com.davada.domain.common.vo.*;
import com.davada.domain.retailshop.vo.ServicePeriod;
import com.davada.domain.wholesaler.vo.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailShopDto {
    String retailShopUuid;
    String wholesalerUuid;
    String retailShopCode;
    String retailShopName;
    String businessNumber;
    String mobilePhoneNumber;
    Location location;
    CompanyType companyType;
    IndustryType industryType;
    Long bondLimitAmount;
    Contact contact;
    Contact reprsContact;
    Contact chargeContact;
    String licenseNo;
    String uptae;
    String jongmok;
    EmployeeManager salesManager;
    BusinessCategory businessCategory;
    YN containerDeposit;
    ShopStatus retailShopStatus;
    ServicePeriod servicePeriod;
    String description;
    ChargeDetails chargeDetails;
}
