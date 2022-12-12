package com.davada.domain.maintenance.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailMaintenanceItemErrorCodes implements ErrorCodes {
    MAINTENANCE_ITEM_1000("유지관리 상품을 찾을 수 없습니다.[maintenanceItemUuid: {0}]"),
    ;

    private String message;

    RetailMaintenanceItemErrorCodes(String message) {
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
