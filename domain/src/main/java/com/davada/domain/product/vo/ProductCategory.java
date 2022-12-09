package com.davada.domain.product.vo;

import lombok.Getter;

/**
 * I01	품목주류구분
 */
public enum ProductCategory {
    ALCOHOL("주류"),
    NONE_ALCOHOL("비주류"),
    CONTAINER("용공");

    @Getter
    private final String description;

    ProductCategory(String description) {
        this.description = description;
    }
}