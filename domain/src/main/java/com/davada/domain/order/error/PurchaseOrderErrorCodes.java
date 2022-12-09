package com.davada.domain.order.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum PurchaseOrderErrorCodes implements ErrorCodes {
    PURCHASE_ORDER_1000("매입주문을 찾을 수 없습니다.[purchaseOrderUuid: {0}]"),
    ;

    private String message;

    PurchaseOrderErrorCodes(String message) {
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
