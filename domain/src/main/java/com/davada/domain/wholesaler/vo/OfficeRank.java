package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * U03	직급(직책)
 */
public enum OfficeRank {
    CEO("최고책임자"),
    COO("최고운영책임자"),
    CFO("최고재무책임자"),
    CIO("최고정보책임자"),
    NONE("선택");

    @Getter
    private final String description;

    OfficeRank(String description) {
        this.description = description;
    }
}
