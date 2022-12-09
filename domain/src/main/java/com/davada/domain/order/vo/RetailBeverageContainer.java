package com.davada.domain.order.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailBeverageContainer {
    String beverageContainerUuid;
    String beverageContainerCode;
    String beverageContainerName;
    BigDecimal sellingContainerDeposit;
    BigDecimal sellingBottleDeposit;
}
