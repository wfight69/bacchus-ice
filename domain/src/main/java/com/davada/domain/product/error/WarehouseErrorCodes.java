package com.davada.domain.product.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum WarehouseErrorCodes implements ErrorCodes {
    WAREHOUSE_1000("창고를을 찾을 수 없습니다.[warehouseUuid: {0}]"),
    WAREHOUSE_1001("같은 창고 코드가 이미 존재 합니다. [warehouseCode: {0}]"),
    WAREHOUSE_1002("창고 코드를 확인하세요. [warehouseCode: {0}]"),
    ;

    private String message;

    WarehouseErrorCodes(String message) {
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
