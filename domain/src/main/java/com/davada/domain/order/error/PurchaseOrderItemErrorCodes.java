package com.davada.domain.order.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum PurchaseOrderItemErrorCodes implements ErrorCodes {
    PURCHASE_ORDER_ITEM_1000("매입 상품을 찾을 수 없습니다.[purchaseOrderItemUuid: {0}]"),
    ;

    private String message;

    PurchaseOrderItemErrorCodes(String message) {
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
