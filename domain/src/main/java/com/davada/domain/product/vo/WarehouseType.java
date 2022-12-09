package com.davada.domain.product.vo;

import lombok.Getter;

public enum WarehouseType {
    //창고
    WAREHOUSE("창고"),
    //장소
    PLACE("장소"),
    //공장
    FACTORY("공장"),
    //기타
    ETC("기타");

    @Getter
    private final String description;

    WarehouseType(String description) {
        this.description = description;
    }
}