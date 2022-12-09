package com.davada.domain.product.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum PurchaseUnitPriceErrorCodes implements ErrorCodes {
    PURCHASE_UNIT_PRICE_1000("매입단가를 찾을 수 없습니다.[purchaseUnitPriceUuid: {0}]"),
    ;

    private String message;

    PurchaseUnitPriceErrorCodes(String message) {
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
