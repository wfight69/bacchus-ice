package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * E05	ARS음성업체
 */
public enum ArsType {

    LG("LG"),
    LGIMS("LGIMS"),
    SAMSUNG("삼성"),
    SK("SK"),
    SKB("SK브로드밴드");
    @Getter
    private final String description;

    ArsType(String description) {
        this.description = description;
    }
}
