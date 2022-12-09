package com.davada.domain.payment.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum LoanErrorCodes implements ErrorCodes {
    LOAN_1000("대여금을 찾을 수 없습니다.[loanUuid: {0}]"),
    ;

    private String message;

    LoanErrorCodes(String message) {
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
