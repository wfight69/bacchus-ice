package com.davada.domain.common.vo;

import lombok.Getter;

/**
 * E02	업체구분
 */
public enum CompanyType {
    PRIVATE("개인"),
    CORPORATION("법인"),
    LIMITED("유한"),
    ETC("기타");

    @Getter
    private final String description;

    CompanyType(String description) {
        this.description = description;
    }
}

