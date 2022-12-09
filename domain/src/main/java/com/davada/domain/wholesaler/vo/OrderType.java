package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * E05	주문서비스타입
 */
public enum OrderType {
    ALL("전체"),
    ARS("음성"),
    SMS("문자"),
    MOBILE("코드(소매점)");

    @Getter
    private final String description;

    OrderType(String description) {
        this.description = description;
    }
}
