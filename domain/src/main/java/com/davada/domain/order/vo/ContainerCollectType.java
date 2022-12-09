package com.davada.domain.order.vo;

import lombok.Getter;

/**
 * 회수 유형
 */
public enum ContainerCollectType {
    COLLECT("회수"),
    CLOSURE("폐업"),
    EXCHANGE("교환"),
    ETC("기타");
    @Getter
    private final String description;

    ContainerCollectType(String description) {
        this.description = description;
    }
}