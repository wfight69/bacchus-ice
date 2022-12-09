package com.davada.domain.product.vo;

import lombok.Getter;

/**
 * 주요상품
 */
public enum KeyProduct {
    ENABLED("설정"),
    DISABLED("미설정"),
    TAG_PRODUCT("태그상품");

    @Getter
    private final String description;

    KeyProduct(String description) {
        this.description = description;
    }
}