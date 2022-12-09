package com.davada.domain.wholesaler.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum EmployeeErrorCodes implements ErrorCodes {
    EMPLOYEE_1000("사원을 찾을 수 없습니다.[employeeUuid: {0}]"),
    EMPLOYEE_1001("같은 로그인 아이디가 이미 존재 합니다. [erpUserLoginId: {0}]"),
    EMPLOYEE_1002("같은 사원코드가 이미 존재 합니다. [employeeCode: {0}]"),
    ;

    private String message;

    EmployeeErrorCodes(String message) {
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
