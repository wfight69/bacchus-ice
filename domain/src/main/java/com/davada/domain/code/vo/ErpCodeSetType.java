package com.davada.domain.code.vo;

public enum ErpCodeSetType {
    ERP("00"),
    SYSTEM("01");

    private String code;

    ErpCodeSetType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
