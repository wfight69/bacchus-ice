package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * E05	VAN업체
 */
public enum VanType {
    KSNET("Ksnet"),
    KTFT("금융결제원");

    @Getter
    private final String description;

    VanType(String description) {
        this.description = description;
    }
}
