package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * U04	직종
 */
public enum OccupationalCategory {
    WHOLESALE_RETAIL("도소매"),
    DISTRIBUTION("유통"),
    MANUFACTURE("제조"),
    EDUCATION("교육"),
    SW("SW개발"),
    NONE("선택");

    @Getter
    private final String description;

    OccupationalCategory(String description) {
        this.description = description;
    }
}
