package com.davada.domain.product.vo;

import lombok.Getter;

/**
 * I04	용기공병 구분
 */
public enum BeverageContainerType {
    //P-Box
    P_BOX("P-Box"),
    //G-Box
    G_BOX("G-Box"),
    //CAN
    CAN("CAN"),
    //생통
    LIQUOR_BARREL("생통"),
    //CO2
    CO2("CO2"),
    //질소
    NITROGEN("질소"),
    //빠레트
    PALLET("빠레트"),
    //공병
    EMPTY_BOTTLE("공병");

    @Getter
    private final String description;

    BeverageContainerType(String description) {
        this.description = description;
    }
}