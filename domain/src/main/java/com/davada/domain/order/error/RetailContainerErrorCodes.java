package com.davada.domain.order.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailContainerErrorCodes implements ErrorCodes {
    RETAIL_CONTAINER_1000("용기공병 회수를 찾을 수 없습니다.[retailContainerUuid: {0}]"),
    ;

    private String message;

    RetailContainerErrorCodes(String message) {
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
