package com.davada.domain.product.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum SellingSpecificUnitPriceErrorCodes implements ErrorCodes {
    SELLING_SPECIFIC_UNIT_PRICE_1000("소매점 판매단가를 찾을 수 없습니다.[sellingUnitPriceUuid: {0}]"),
    SELLING_SPECIFIC_UNIT_PRICE_1001("retailShopUuid 입력 확인"),
    SELLING_SPECIFIC_UNIT_PRICE_1002("이 상품에 대한 소매점 판매단가가 이미 존재합니다.[productUuid: {0}, sellingSpecificUnitPriceUuid: {1}]"),
    ;

    private String message;

    SellingSpecificUnitPriceErrorCodes(String message) {
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
