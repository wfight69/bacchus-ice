package com.davada.domain.payment.vo;

import lombok.Getter;

public enum VatType {

    TAX("세금계산서"),
    TX_STATEMENT("거래명세서"),
    CASH_RECEIPT("현금영수증"),
    ETC("기타");

    @Getter
    private final String description;

    VatType(String description) {
        this.description = description;
    }
}
