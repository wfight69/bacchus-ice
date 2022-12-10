package com.davada.domain.maintenance.vo;

import lombok.Getter;

/**
 * 유지관리 INPUT 경로
 */
public enum RetailMaintenanceChannel {
    VOICE("전화"), // 주류도매업체 담당자가 전화로 A/S요청
    DIRECT("주류도매"), // 주류도매업체 담당자가 직접 A/S 등록
    APP("소매점"); // 소매점에서 모바일 앱으로 A/S 등록

    @Getter
    private final String description;

    RetailMaintenanceChannel(String description) {
        this.description = description;
    }
}