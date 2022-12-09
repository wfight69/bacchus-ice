package com.davada.application.supplier.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.*;
import com.davada.domain.supplier.entity.Supplier;
import com.davada.domain.wholesaler.vo.Location;
import com.davada.dto.supplier.SupplierDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface SupplierCrudUseCase {
    IdValue createSupplier(Supplier supplier);

    SupplierDto retrieveSupplier(String supplierUuid);

    List<SupplierDto> retrieveAllSupplier(String wholesalerUuid);

    BooleanValue updateSupplier(String supplierUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteSupplier(String supplierUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateSupplierCommand {
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
        //String purchasePersonName;
        String employeeUuid;
        YN substitutionYn;
        ShopStatus supplierStatus;
        String description;
    }
}
