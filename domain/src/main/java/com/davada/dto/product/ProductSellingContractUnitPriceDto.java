package com.davada.dto.product;

import com.davada.domain.product.vo.BeverageCategory;
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
public class ProductSellingContractUnitPriceDto {
    String sellingSpecificUnitPriceUuid;
    String productUuid;
    String retailShopUuid;

    String productCode;
    String productName;
    String volume;
    Integer bottlesInBox;
    BeverageCategory beverageCategory;

    BigDecimal profitMarginRate;
    //상자: 소계금액, 공급가, 부가세
    UnitPrice containerPrice;
    //본수: 소계금액, 공급가, 부가세
    UnitPrice bottlePrice;

    //상자: 소계금액, 공급가, 부가세
    UnitPrice productContainerPrice;
    //본수: 소계금액, 공급가, 부가세
    UnitPrice productBottlePrice;
}

