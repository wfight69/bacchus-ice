package com.davada.domain.purchase.vo;

import lombok.Getter;

/**
 * 마감 상태
 */
public enum CalculateStatus {
    // 미마감
    ACTIVE("미마감"),
    // 마감
    CLOSED("마감");
    @Getter
    private final String description;

    CalculateStatus(String description) {
        this.description = description;
    }
}