package com.davada.domain.product.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum BeverageContainerErrorCodes implements ErrorCodes {
    BEVERAGE_CONTAINER_1000("용기공병을 찾을 수 없습니다.[beverageContainerUuid: {0}]"),
    BEVERAGE_CONTAINER_1001("같은 용기공병 코드가 이미 존재 합니다. [beverageCaseCode: {0}]"),
    BEVERAGE_CONTAINER_1002("용기공병 코드를 확인하세요. [beverageCaseCode: {0}]"),
    ;

    private String message;

    BeverageContainerErrorCodes(String message) {
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
