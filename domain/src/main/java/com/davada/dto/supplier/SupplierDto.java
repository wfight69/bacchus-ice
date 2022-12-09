package com.davada.dto.supplier;

import com.davada.domain.common.vo.*;
import com.davada.domain.wholesaler.vo.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupplierDto {
    String supplierUuid;
    String wholesalerUuid;
    String supplierCode;
    String supplierName;
    String businessNumber;
    String mobilePhoneNumber;
    Location location;
    CompanyType companyType;
    IndustryType industryType;
    Contact contact;
    Contact reprsContact;
    Contact chargeContact;
    String uptae;
    String jongmok;
    EmployeeManager purchaseManager;
    YN substitutionYn;
    ShopStatus supplierStatus;
    String description;
}
