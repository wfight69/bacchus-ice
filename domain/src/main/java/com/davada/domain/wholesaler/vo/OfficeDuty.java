package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * U02	직무
 */
public enum OfficeDuty {
    SALES("영업담당"),
    PURCHASE("구매담당"),
    MANAGER("총무담당"),
    SHIPPING("운송담당"),
    STOCK("재고담당"),
    AS("AS담당"),
    CLEANING("청소담당"),
    NONE("선택");

    @Getter
    private final String description;

    OfficeDuty(String description) {
        this.description = description;
    }
}
