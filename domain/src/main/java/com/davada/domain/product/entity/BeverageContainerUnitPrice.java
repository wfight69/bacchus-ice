package com.davada.domain.product.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 용기공병 보증금 정보
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeverageContainerUnitPrice {
    //String beverageContainerUnitPriceUuid;
    // 매입보증금
    BigDecimal purchaseDeposit;
    // 매출보증금
    BigDecimal sellingDeposit;
    // 매입수수료
    BigDecimal purchaseFees;
    // 반납수수료(용차)
    BigDecimal returnCallServiceFees;
    // 반납수수료(자차)
    BigDecimal returnSelfServiceFees;
}
