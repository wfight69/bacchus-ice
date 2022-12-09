package com.davada.domain.icesaler.vo;

import lombok.Getter;

/**
 * E04	진행상태
 */
public enum IcesalerStatus {
    ACTIVE("진행중"),
    INACTIVE("일시정지"),
    CLOSED("정지(종료)"),
    ETC("기타");

    @Getter
    private final String description;

    IcesalerStatus(String description) {
        this.description = description;
    }
}