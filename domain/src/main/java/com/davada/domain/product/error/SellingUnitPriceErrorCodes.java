package com.davada.domain.product.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum SellingUnitPriceErrorCodes implements ErrorCodes {
    SELLING_UNIT_PRICE_1000("판매단가를 찾을 수 없습니다.[sellingUnitPriceUuid: {0}]"),
    SELLING_UNIT_PRICE_1001("productUuid 입력 확인"),
    ;

    private String message;

    SellingUnitPriceErrorCodes(String message) {
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
