package com.davada.domain.common.vo;

import lombok.Getter;

/**
 * E03	업체유형
 */
public enum IndustryType {
    ALCOHOLIC("주류"),
    MANUFACTURING("제조사"),
    REFRIGERATION("냉동냉장"),
    FRANCHISE("체인"),
    FOOD_INGREDIENTS("식자재"),
    DISCOUNT_STORE("할인매장(마트)"),
    CONVENIENCE_STORE("편의점"),
    TEAM("담당팀"),
    ETC("기타");

    @Getter
    private final String description;

    IndustryType(String description) {
        this.description = description;
    }
}