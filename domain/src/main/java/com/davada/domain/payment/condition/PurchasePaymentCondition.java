package com.davada.domain.payment.condition;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchasePaymentCondition {
    String wholesalerUuid;
    String supplierUuid;
    String supplierCode;
    String supplierName;
    // 전표일자
    String startCreateDate;
    String endCreateDate;
    // 출금일자
    String startPaymentDate;
    String endPaymentDate;
}
