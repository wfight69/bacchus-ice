package com.davada.dto.payment;

import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.VatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentDto {
    String paymentUuid;
    String wholesalerUuid;
    String supplierUuid;
    String supplierCode;
    String supplierName;
    String roadAddress;
    String roadAddressDetails;
    String representativeName;
    String representativeMobile;
    String representativeTelephone;
    String createDate;
    String paymentDate;
    VatType vatType;
    Integer closeBondAmount;
    Integer balanceBondAmount;
    Integer paymentAmount;
    Integer containerOffsetAmount;
    Integer totalPaymentAmount;
    String bankName;
    String bankAccount;
    YN closeYn;
    String description;
}