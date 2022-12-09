package com.davada.domain.order.vo;

import com.davada.domain.product.vo.UnitPrice;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailPurchaseUnitPrice {
    String purchaseUnitPriceUuid;
    String productUuid;
    UnitPrice containerPrice;
    UnitPrice bottlePrice;
    BigDecimal totalPrice;
    String applyStartDate;
    String applyEndDate;
}
