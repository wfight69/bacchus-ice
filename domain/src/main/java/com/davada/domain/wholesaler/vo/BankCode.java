package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * E06	은행코드
 */
public enum BankCode {
    SHINHAN("88", "신한"),
    NOGHYUP("33", "농협"),
    KUKMIN("90", "국민"),
    ETC("99", "기타");

    @Getter
    private final String code;
    @Getter
    private final String description;

    BankCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

