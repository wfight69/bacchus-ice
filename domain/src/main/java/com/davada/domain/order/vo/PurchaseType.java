package com.davada.domain.order.vo;

import lombok.Getter;

/**
 * 매입유형
 */
public enum PurchaseType {
    PURCHASE("구매"),
    RETURN("환출"),
    CONTAINER("용기공병"),
    ETC("기타");
    @Getter
    private final String description;

    PurchaseType(String description) {
        this.description = description;
    }
}