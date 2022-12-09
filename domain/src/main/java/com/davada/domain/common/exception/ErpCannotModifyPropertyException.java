package com.davada.domain.common.exception;

import com.davada.domain.common.util.StringHelper;

import java.util.List;

public class ErpCannotModifyPropertyException extends ErpRuntimeException {
    public ErpCannotModifyPropertyException(String propertyName) {
        super("CANNOT_MODIFY_PROPERTY", propertyName);
    }

    public ErpCannotModifyPropertyException(List<String> propertyNames) {
        super("CANNOT_MODIFY_PROPERTY", StringHelper.join(propertyNames));
    }
}