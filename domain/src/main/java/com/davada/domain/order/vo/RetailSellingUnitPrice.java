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
public class RetailSellingUnitPrice {
    Boolean isContractPrice; // 소매점 계약단가 여부
    BigDecimal profitMarginRate;
    UnitPrice containerPrice;
    UnitPrice bottlePrice;
}
