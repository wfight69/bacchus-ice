package com.davada.domain.order.vo;

import lombok.Getter;

public enum TransportType {
    CALL("용차"),
    SELF("자차");
    @Getter
    private final String description;

    TransportType(String description) {
        this.description = description;
    }
}