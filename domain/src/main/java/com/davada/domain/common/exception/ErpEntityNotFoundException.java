package com.davada.domain.common.exception;

public class ErpEntityNotFoundException extends ErpRuntimeException {
    public ErpEntityNotFoundException(String message) {
        super("ENTITY_NOT_FOUND", message);
    }
}
