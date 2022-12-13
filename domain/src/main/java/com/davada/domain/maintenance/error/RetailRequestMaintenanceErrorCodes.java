package com.davada.domain.maintenance.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailRequestMaintenanceErrorCodes implements ErrorCodes {
    REQUEST_MAINTENANCE_1000("유지관리요청을 찾을 수 없습니다.[requestOrderUuid: {0}]"),
    REQUEST_MAINTENANCE_1001("유지관리이 판매되었습니다, 유지관리삭제는 판매유지관리을 먼저 취소하십시요."),
    REQUEST_MAINTENANCE_2001("주류도매업체를 찾을 수 없습니다.[wholesalerUuid: {0}]"),
    REQUEST_MAINTENANCE_2002("유지관리자의 연락처 정보가 없습니다.[retailOrderTelephone: {0}]"),
    ;

    private String message;

    RetailRequestMaintenanceErrorCodes(String message) {
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
