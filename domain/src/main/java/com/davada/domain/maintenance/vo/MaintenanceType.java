package com.davada.domain.maintenance.vo;

import lombok.Getter;

/**
 * 관리유형
 */
public enum MaintenanceType {
    SALE("출고"), // 출고==판매
    REPAIR("수리"),
    RETURN("회수"),
    OVERHAUL("정비"),
    ETC("기타");
    @Getter
    private final String description;

    MaintenanceType(String description) {
        this.description = description;
    }
}