package com.davada.domain.payment.vo;

import lombok.Getter;

public enum IssueTaxStatus {
    PREPARING("전자세금계산서 작성중"),
    SUCCESS("발급완료"),
    FAIL("발급실패"),
    CANCEL("발급취소"),
    ;
    @Getter
    private final String description;

    IssueTaxStatus(String description) {
        this.description = description;
    }
}
