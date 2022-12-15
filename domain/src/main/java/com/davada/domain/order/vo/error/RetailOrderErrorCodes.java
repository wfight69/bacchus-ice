package com.davada.domain.order.vo.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailOrderErrorCodes implements ErrorCodes {
    ORDER_1000("주문을 찾을 수 없습니다.[orderUuid: {0}]"),
    ORDER_2000("DIRECT, APP 주문만 등록 가능합니다. [retailOrderChannel: {0}]"),
    ;

    private String message;

    RetailOrderErrorCodes(String message) {
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
