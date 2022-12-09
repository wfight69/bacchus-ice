package com.davada.domain.retailshop.condition;

import com.davada.domain.common.vo.ShopStatus;
import com.davada.domain.wholesaler.vo.Province;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailShopCondition {
    String wholesalerUuid;
    // 진행구분
    ShopStatus retailShopStatus;
    // 영업담당자코드
    String salesPersonCode;
    // 지역구분코드
    Province province;
    // 납품처코드
    String retailShopCode;
    // 상호명
    String retailShopName;
    // 대표자명
    String representativeName;
    // 폰번호 for SMS service
    String mobilePhoneNumber;
}