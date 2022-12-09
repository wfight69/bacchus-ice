package com.davada.dto.payment;

import com.davada.domain.payment.vo.VatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanDto {
    String loanUuid;
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    String retailShopTelephone;
    String roadAddress;
    String roadAddressDetails;
    String representativeName;
    String representativeTelephone;
    String employeeUuid;
    String employeeCode;
    String employeeName;
    VatType vatType;
    String createDate;
    String loanDate;
    Integer loanAmount;
    Integer loanFee;
    Integer installmentPlan;
    Integer repaymentAmount;
    Integer balanceAmount;
    String bankName;
    String bankAccount;
    String description;
    // Set<LoanInstallment> loanInstallments = new HashSet<>();
}
