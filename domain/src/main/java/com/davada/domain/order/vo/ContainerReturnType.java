package com.davada.domain.order.vo;

import lombok.Getter;

/**
 * 반납 유형
 */
public enum ContainerReturnType {
    RETURN("반납"),
    ETC("기타");
    @Getter
    private final String description;

    ContainerReturnType(String description) {
        this.description = description;
    }
}