package com.davada.dto.payment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanInstallmentDto {
    String loanInstallmentUuid;
    String loanUuid;
    Integer sequenceNumber;
    String createDate;
    String repaymentDate;
    Integer repaymentAmount;
    Integer balanceAmount;
    String bankName;
    String bankAccount;
    String description;
}
