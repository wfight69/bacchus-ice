package com.davada.domain.maintenance.vo;

import lombok.Getter;

/**
 * 유지관리 상태
 */
public enum RetailMaintenanceStatus {
    REJECTED("거절"),
    CANCELLED("취소"),
    RECEIVED("등록"),  // 주류도매 외부api등록시
    ACCEPTED("접수"),  // 전화및 기타체널로 등록시
    RELEASING("요청"), // as담당자 전송처리시
    RELEASED("진행"),
    DELIVERED("완료");

    @Getter
    private final String description;

    RetailMaintenanceStatus(String description) {
        this.description = description;
    }
}