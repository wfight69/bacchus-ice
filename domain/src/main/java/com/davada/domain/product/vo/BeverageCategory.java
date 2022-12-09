package com.davada.domain.product.vo;

import lombok.Getter;

/**
 * I02	품목주종구분
 */
public enum BeverageCategory {
    SOJU("소주"),
    DILUTED_SOJU("희석식소주"),
    DISTILLED_SOJU("증류식소주"),
    BEER("맥주"),
    IMPORTED_BEER("수입맥주"),
    LAGER_SOJU("라거맥주"),
    DRAFT_BEER("생맥주"),
    WHISKEY("위스키"),
    IMPORTED_WHISKEY("수입위스키"),
    BRANDY("브랜디"),
    DISTILLED_ALCOHOL("일반증류주"),
    VODKA("보드카"),
    LIQUEUR("리큐르"),
    FRUIT_WINE("과실주"),
    WINE("와인"),
    WHITE_WINE("화이트와인"),
    CHEONGJU("청주"),
    KAOLIANG("고량주"),
    ETC("기타주류"),
    MIXED_LIQUOR("제재주"),
    RICE_WINE("탁주"),
    HERBAL_LIQUOR("약주"),
    NON_ALCOHOL("비주류");

    @Getter
    private final String description;

    BeverageCategory(String description) {
        this.description = description;
    }
}
