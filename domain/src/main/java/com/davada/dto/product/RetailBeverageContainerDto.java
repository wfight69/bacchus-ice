package com.davada.dto.product;

import com.davada.domain.product.vo.BeverageContainerType;
import com.davada.domain.product.vo.BrewingType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailBeverageContainerDto {
    String beverageContainerUuid;
    String beverageContainerCode;
    BeverageContainerType beverageContainerType;
    BrewingType brewingType;
    String beverageContainerName;
    String volume;
    String measurement;
    Integer bottlesInBox;
    String description;
    Integer stockQuantity;
    BigDecimal sellingContainerDeposit;
    BigDecimal sellingBottleDeposit;
}
