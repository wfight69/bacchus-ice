package com.davada.application.retailshop.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.entity.ChargeDetails;
import com.davada.domain.common.vo.*;
import com.davada.domain.retailshop.entity.RetailShop;
import com.davada.domain.retailshop.vo.ServicePeriod;
import com.davada.domain.wholesaler.vo.Location;
import com.davada.dto.retailshop.RetailShopDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

public interface RetailShopCrudUseCase {
    //IdValue createRetailShop(CreateRetailShopCommand command);

    IdValue createRetailShop(RetailShop command);

    RetailShopDto retrieveRetailShop(String retailShopUuid);
    List<RetailShopDto> retrieveAllRetailShop(String wholesalerUuid);

    BooleanValue updateRetailShop(String retailShopUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteRetailShop(String retailShopUuid, boolean permanent);

    Optional<RetailShopDto> findByRetailOrderTelephone(String telephone);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateRetailShopCommand {
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
        String employeeUuid;
        BusinessCategory businessCategory;
        YN containerDeposit;
        ShopStatus retailShopStatus;
        ServicePeriod servicePeriod;
        String description;
        ChargeDetails chargeDetails;
    }

}
