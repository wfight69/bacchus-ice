package com.davada.domain.code.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum ErpCodeErrorCodes implements ErrorCodes {
    CODE_0001("코드가 이미 존재합니다. [코드명: {0}]"),
    CODE_0002("코드가 존재하지 않습니다. [codeUuid: {0}]"),
    ;

    private String message;

    ErpCodeErrorCodes(String message) {
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
