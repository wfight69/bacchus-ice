package com.davada.domain.supplier.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum SupplierErrorCodes implements ErrorCodes {
    SUPPLIER_1000("매입처를 찾을 수 없습니다.[supplierUuid: {0}]"),
    SUPPLIER_1001("같은 매입처 코드가 이미 존재 합니다. [supplierCode: {0}]"),
    SUPPLIER_1002("매입처 코드를 확인하세요. [supplierCode: {0}]"),
    ;

    private String message;

    SupplierErrorCodes(String message) {
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
