package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * E04	진행상태
 */
public enum WholesalerStatus {
    ACTIVE("진행중"),
    INACTIVE("일시정지"),
    CLOSED("정지(종료)"),
    ETC("기타");

    @Getter
    private final String description;

    WholesalerStatus(String description) {
        this.description = description;
    }
}