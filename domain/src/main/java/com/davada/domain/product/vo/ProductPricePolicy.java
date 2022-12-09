package com.davada.domain.product.vo;

import lombok.Getter;

/**
 * 상품 단가정책
 */
public enum ProductPricePolicy {
    GENERAL_PRICE_POLICY("일반 단가 정책 적용 상품"),
    ENT_PRICE_POLICY("유흥 단가 정책 적용 상품"),
    DUAL_PRICE_POLICY("일반/유흥 단가 정책 적용 상품");

    @Getter
    private final String description;

    ProductPricePolicy(String description) {
        this.description = description;
    }
}