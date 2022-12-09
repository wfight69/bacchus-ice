package com.davada.domain.order.vo;

import lombok.Getter;
/**
 * 주문요청 상태
 */
public enum RetailRequestOrderStatus {
    RECEIVED("주문접수"),
    ACCEPTED("주문처리");

    @Getter
    private final String description;

    RetailRequestOrderStatus(String description) {
        this.description = description;
    }
}