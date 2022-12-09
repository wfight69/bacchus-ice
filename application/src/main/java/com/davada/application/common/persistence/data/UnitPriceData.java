package com.davada.application.common.persistence.data;

import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnitPriceData {
    private BigDecimal price;
    private BigDecimal vat;
    private BigDecimal subtotal;
}
