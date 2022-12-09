package com.davada.domain.payment.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum LoanInstallmentErrorCodes implements ErrorCodes {
    LOAN_INSTALLMENT_1000("대여금 입금을 찾을 수 없습니다.[loanInstallmentUuid: {0}]"),
    ;

    private String message;

    LoanInstallmentErrorCodes(String message) {
        this.message = message;
    }

    @Override
    public String errorCode() {
        return name();
    }

    @Override
    public String errorMessage() {
        return this.message;
    }
}
