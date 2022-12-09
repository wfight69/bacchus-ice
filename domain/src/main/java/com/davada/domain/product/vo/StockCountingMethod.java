package com.davada.domain.product.vo;

import lombok.Getter;

public enum StockCountingMethod {
    PRODUCT_CONTAINER("상품+용공재고"),
    ONLY_CONTAINER("용공재고");

    @Getter
    private final String description;

    StockCountingMethod(String description) {
        this.description = description;
    }
}