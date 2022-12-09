package com.davada.domain.order.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum PurchaseContainerErrorCodes implements ErrorCodes {
    PURCHASE_CONTAINER_1000("용기공병 반납을 찾을 수 없습니다.[purchaseContainerUuid: {0}]"),
    ;

    private String message;

    PurchaseContainerErrorCodes(String message) {
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
