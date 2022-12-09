package com.davada.domain.retailshop.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailOrderTelephoneErrorCodes implements ErrorCodes {
    RETAILORDERTELEPHONE_1000("주문전화번호를 찾을 수 없습니다.[retailOrderTelephoneUuid: {0}]"),
    RETAILORDERTELEPHONE_1001("같은 주문전화번호가 이미 존재 합니다. [telephone: {0}]"),
            ;

    private String message;

    RetailOrderTelephoneErrorCodes(String message) {
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
