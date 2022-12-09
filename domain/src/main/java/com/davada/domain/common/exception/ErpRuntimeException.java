package com.davada.domain.common.exception;

import static com.davada.domain.common.util.StringHelper.defaultIfEmpty;
import static com.davada.domain.common.util.StringHelper.format;


public class ErpRuntimeException extends RuntimeException {
    private String errorCode;
    private String message;

    protected ErpRuntimeException() {
        //
    }

    public ErpRuntimeException(Exception e) {
        this.errorCode = "INTERNAL_ERROR";
        this.message = defaultIfEmpty(e.getMessage(), e.getClass().getSimpleName());
    }

    public ErpRuntimeException(ErrorCodes errorCodes) {
        super(errorCodes.errorMessage());
        this.message = errorCodes.errorMessage();
        this.errorCode = errorCodes.errorCode();
    }

    public ErpRuntimeException(ErrorCodes errorCodes, Object... values) {
        this.message = format(errorCodes.errorMessage(), values);
        this.errorCode = errorCodes.errorCode();
    }

    public ErpRuntimeException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErpRuntimeException(String errorCode, String message, Object... values) {
        this.errorCode = errorCode;
        this.message = format(message, values);
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
