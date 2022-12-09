package com.davada.domain.wholesaler.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum WholesalerErrorCodes implements ErrorCodes {
    WHOLESALER_1000("주류도매업체를 찾을 수 없습니다.[wholesalerUuid: {0}]"),
    WHOLESALER_1001("같은 주류도매업체 코드가 이미 존재 합니다. [wholesalerCode: {0}]"),
    WHOLESALER_1002("주류도매업체 코드를 확인하세요. [wholesalerCode: {0}]"),
    ;

    private String message;

    WholesalerErrorCodes(String message) {
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
