package com.davada.domain.payment.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum PurchasePaymentErrorCodes implements ErrorCodes {
    PURCHASE_PAYMENT_1000("매입주문 출금금액을 찾을 수 없습니다.[purchasePaymentUuid: {0}]"),
    ;

    private String message;

    PurchasePaymentErrorCodes(String message) {
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
