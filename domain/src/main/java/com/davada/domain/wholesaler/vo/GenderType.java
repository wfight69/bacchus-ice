package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * U05	성별
 */
public enum GenderType {
    MALE("남자"),
    FEMALE("여자");

    @Getter
    private final String description;

    GenderType(String description) {
        this.description = description;
    }
}
