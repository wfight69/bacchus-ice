package com.davada.domain.product.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum ProductErrorCodes implements ErrorCodes {
    PRODUCT_1000("품목을 찾을 수 없습니다.[productUuid: {0}]"),
    PRODUCT_1001("같은 품목 코드가 이미 존재 합니다. [productCode: {0}]"),
    PRODUCT_1002("품목 코드를 확인하세요. [productCode: {0}]"),
    PRODUCT_2001("[retailShopUuid]는 필수 입력값입니다."),
    PRODUCT_2002("[businessCategory]는 필수 입력값입니다."),
    ;

    private String message;

    ProductErrorCodes(String message) {
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
