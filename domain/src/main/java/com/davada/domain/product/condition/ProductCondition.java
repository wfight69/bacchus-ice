package com.davada.domain.product.condition;

import com.davada.domain.common.vo.BusinessCategory;
import com.davada.domain.common.vo.YN;
import com.davada.domain.product.vo.BeverageCategory;
import com.davada.domain.product.vo.BeverageContainerType;
import com.davada.domain.product.vo.ProductCategory;
import com.davada.domain.product.vo.ProductPricePolicy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCondition {
    //--------------------------------------------------------
    // 필수입력항목 for RetailProduct
    String retailShopUuid;
    BusinessCategory businessCategory;
    //--------------------------------------------------------
    String wholesalerUuid;
    String productUuid;
    ProductPricePolicy productPricePolicy;
    BeverageContainerType beverageContainerType;
    ProductCategory productCategory;
    BeverageCategory beverageCategory;
    String productCode;
    String productName;
    String productAliasName;
    YN safetyStockExists;
}