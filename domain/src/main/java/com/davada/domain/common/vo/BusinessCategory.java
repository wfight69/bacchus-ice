package com.davada.domain.common.vo;

import lombok.Getter;

/**
 * T02	거래처업종구분
 */
public enum BusinessCategory {
    //일반
    GENERAL_STORE("일반"),
    //유흥
    ENT_STORE("유흥"),
    //할인매장
    DISCOUNT_STORE("할인매장"),
    //면세
    TAX_FREE_STORE("면세"),
    //영세
    SMALL_STORE("영세"),
    //차량
    VEHICLE("차량");

    @Getter
    private final String description;

    BusinessCategory(String description) {
        this.description = description;
    }
}