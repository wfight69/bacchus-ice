package com.davada.domain.payment.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 대여금 상환 입금 회차
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanInstallment extends AuditableEntity implements Refinable {
    // UUID
    String loanInstallmentUuid;
    // 대여금 UUID
    String loanUuid;
    // 회차
    Integer sequenceNumber;
    // 전표일자
    String createDate;
    // 입금일자
    String repaymentDate;
    // 입금액
    Integer repaymentAmount;
    //  잔여금액
    Integer balanceAmount;
    // 입금은행
    String bankName;
    // 입금계좌
    String bankAccount;
    // 적요
    String description;

    @Override
    public void refineValues() {

    }

}
