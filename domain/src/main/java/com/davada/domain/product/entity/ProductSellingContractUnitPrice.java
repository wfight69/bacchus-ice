package com.davada.domain.product.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.product.vo.UnitPrice;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 상품 판매 소매점 계약단가
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSellingContractUnitPrice extends AuditableEntity implements Refinable {
    //판매단가 UUID
    String sellingSpecificUnitPriceUuid;
    //품목 UUID
    String productUuid;
    //소매점 UUID
    String retailShopUuid;

    //마진율
    BigDecimal profitMarginRate;
    //상자: 소계금액, 공급가, 부가세
    UnitPrice containerPrice;
    //본수: 소계금액, 공급가, 부가세
    UnitPrice bottlePrice;

    @Override
    public void refineValues() {

    }
}

