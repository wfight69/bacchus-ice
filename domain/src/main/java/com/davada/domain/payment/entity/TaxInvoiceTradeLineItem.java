package com.davada.domain.payment.entity;

import com.davada.domain.common.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaxInvoiceTradeLineItem implements Entity {
    String taxInvoiceTradeLineItemUuid;
    // 공급일자: YYYYMMDD 형식
    String purchaseExpiry;
    // 품목
    String name;
    // 규격
    String information;
    // 수량
    String chargeableUnit;
    // 단가
    String unitPrice;
    // 공급가액
    String amount;
    // 세액
    String tax;
    // 비고
    String description;
}
