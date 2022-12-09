package com.davada.domain.retailshop.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailShopErrorCodes implements ErrorCodes {
    RETAIL_SHOP_1000("납품처를 찾을 수 없습니다.[retailShopUuid: {0}]"),
    RETAIL_SHOP_1001("같은 납품처 코드가 이미 존재 합니다. [retailShopCode: {0}]"),
    RETAIL_SHOP_1002("납품처 입력 확인. [retailShopUuid: {0}]"),
    RETAIL_SHOP_1003("담당 영업사원 입력 확인. [employeeUuid: {0}]"),
    RETAIL_SHOP_1004("납품처 코드를 확인하세요. [retailShopCode: {0}]"),
    ;

    private String message;

    RetailShopErrorCodes(String message) {
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
