package com.davada.dto.product;

import com.davada.domain.product.vo.UnitPrice;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * ProductPricePolicy 에 따라,
 * 일반단가/유흥단가/소매점계약단가
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailProductUnitPriceDto {
    Boolean isContractPrice; // 소매점 계약단가 여부
    BigDecimal profitMarginRate;
    UnitPrice containerPrice;
    UnitPrice bottlePrice;
}
