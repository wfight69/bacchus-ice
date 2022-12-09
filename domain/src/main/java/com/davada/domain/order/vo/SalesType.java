package com.davada.domain.order.vo;

import lombok.Getter;

/**
 * 매출유형
 */
public enum SalesType {
    SALE("판매"),
    RETURN("반품"),
    CHANGE_OVER("환입"),
    DONATED("기증"),
    DAMAGED("파손"),
    CLOSED("차량출고"),
    ETC("기타");
    @Getter
    private final String description;

    SalesType(String description) {
        this.description = description;
    }
}