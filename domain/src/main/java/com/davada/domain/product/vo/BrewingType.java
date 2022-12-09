package com.davada.domain.product.vo;

import lombok.Getter;

public enum BrewingType {
    SOJU("소주류"),
    BEER("맥주류"),
    DRAFT_BEER("생맥주"),
    ETC("기타");

    // 증류주(Distilled Beverage): Liquor(or Spirit), (SOJU) (Whiskey, Rum, Brandy, Vodka, Tequila)
    // 발효주(Fermented Beverage): Beer, Wine, Cidar
    // 혼성주(Compounded Beverage): Cocktail, Liqueur

    @Getter
    private final String description;

    BrewingType(String description) {
        this.description = description;
    }
}