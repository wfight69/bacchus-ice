package com.davada.domain.code.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum ErpCodeSetErrorCodes implements ErrorCodes {
    CODE_SET_0001("코드셋이 이미 존재합니다. [코드셋명: {0}]"),
    CODE_SET_0002("코드셋이 존재하지 않습니다. [codeSetUuid: {0}]"),
    ;

    private String message;

    ErpCodeSetErrorCodes(String message) {
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
