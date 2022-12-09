package com.davada.domain.icesaler.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum IcesalerErrorCodes implements ErrorCodes {
    ICESALER_1000("냉장업체를 찾을 수 없습니다.[icesalerUuid: {0}]"),
    ICESALER_1001("같은 냉장업체 코드가 이미 존재 합니다. [icesalerCode: {0}]"),
    ICESALER_1002("냉장업체 코드를 확인하세요. [icesalerCode: {0}]"),
    ;

    private String message;

    IcesalerErrorCodes(String message) {
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
