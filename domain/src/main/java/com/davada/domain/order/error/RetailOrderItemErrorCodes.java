package com.davada.domain.order.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailOrderItemErrorCodes implements ErrorCodes {
    ORDER_ITEM_1000("주문상품을 찾을 수 없습니다.[orderItemUuid: {0}]"),
    ;

    private String message;

    RetailOrderItemErrorCodes(String message) {
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
