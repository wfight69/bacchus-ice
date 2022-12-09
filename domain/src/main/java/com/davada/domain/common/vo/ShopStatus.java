package com.davada.domain.common.vo;

import lombok.Getter;

/**
 * T03	거래처진행구분
 */
public enum ShopStatus {
    OPEN("판매"),
    CLOSED("영업정지"),
    SHUTDOWN("폐업"),
    SUSPENDED("거래중단"),
    PAUSED("일시정지"),
    BLACKLISTED("관리대상");

    @Getter
    private final String description;

    ShopStatus(String description) {
        this.description = description;
    }
}