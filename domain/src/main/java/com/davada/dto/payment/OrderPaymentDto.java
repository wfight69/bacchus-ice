package com.davada.dto.payment;

import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.VatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderPaymentDto {
    String orderPaymentUuid;
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    String roadAddress;
    String roadAddressDetails;
    String representativeName;
    String representativeMobile;
    String representativeTelephone;
    String employeeUuid;
    String employeeCode;
    String employeeName;
    String createDate;
    String paymentDate;
    VatType vatType;
    Integer balanceAmount;
    String bankName;
    String bankAccount;
    CodeName accountSubject;
    Integer closeBondAmount;
    Integer balanceBondAmount;

    Integer cardPaymentAmount;
    Integer cashPaymentAmount;
    Integer accountPaymentAmount;
    Integer offsettingAmount;
    Integer totalPaymentAmount;

    YN closeYn;
    String description;
}
