package com.davada.domain.board.vo;

public enum DisclosureScope {
    CUSTOMER("거래처"),
    COMPANY("회사"),
    ALL("전체");

    private String code;

    DisclosureScope(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
