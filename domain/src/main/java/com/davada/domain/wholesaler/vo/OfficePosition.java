package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * U01	직위
 */
public enum OfficePosition {
    CHAIRMAN("chairman"),
    PRESIDENT("대표이사"),
    EXECUTIVE("전무"),
    MANAGING_DIRECTOR("상무"),
    DIRECTOR("이사"),
    DEPARTMENT_HEAD("부장"),
    DEPUTY_DEPARTMENT_HEAD("차장"),
    SECTION_CHIEF("과장"),
    DEPUTY_SECTION_CHIEF("대리"),
    WORKER("사원"),
    NONE("선택");

    @Getter
    private final String description;

    OfficePosition(String description) {
        this.description = description;
    }
}
