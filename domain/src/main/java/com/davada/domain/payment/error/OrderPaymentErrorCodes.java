package com.davada.domain.payment.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum OrderPaymentErrorCodes implements ErrorCodes {
    ORDER_PAYMENT_1000("주문 입금금액을 찾을 수 없습니다.[orderPaymentUuid: {0}]"),
    ;

    private String message;

    OrderPaymentErrorCodes(String message) {
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
