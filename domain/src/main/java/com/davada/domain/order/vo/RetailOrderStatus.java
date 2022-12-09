package com.davada.domain.order.vo;

import lombok.Getter;

/**
 * 주문상태
 */
public enum RetailOrderStatus {
    REJECTED("거절"),
    CANCELLED("판매취소"),
    RECEIVED("주문접수"),
    ACCEPTED("주문처리"),
    RELEASING("출고요청"),
    RELEASED("출고완료"),
    DELIVERED("배송완료"); // TODO 배송완료시 RFID 국세청 전송

    @Getter
    private final String description;

    RetailOrderStatus(String description) {
        this.description = description;
    }
}