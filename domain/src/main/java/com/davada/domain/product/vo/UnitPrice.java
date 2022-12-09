package com.davada.domain.product.vo;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnitPrice {
    //공급가
    BigDecimal price;
    //부가세
    BigDecimal vat;
    //소계금액
    BigDecimal subtotal;
}
