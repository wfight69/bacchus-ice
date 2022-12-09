package com.davada.domain.order.entity;

import com.davada.domain.common.Entity;
import com.davada.domain.common.Refinable;
import com.davada.domain.order.vo.RetailBeverageContainer;
import com.davada.domain.product.vo.UnitPrice;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 구매 주문 품목 리스트
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseOrderItem implements Entity, Refinable {
    // 주문 품목 UUID
    String purchaseOrderItemUuid;
    // 상품 UUID
    String productUuid;
    // 상품명
    String productName;
    // 상품코드
    String productCode;
    // 용량
    String volume;
    // 상자당 병수량
    Integer bottlesInBox;
    // 용기보증금, 공병보증금 (용량,본수),
    RetailBeverageContainer beverageContainer;
    // 상품 상자별 단가/부가세
    UnitPrice containerPrice;
    // 상자 주문 개수
    Integer containerQuantity;
    // 본 주문 개수
    Integer bottleQuantity;
    // 보충 (무료 박스 개수)
    Integer supplementQuantity;
    // 공급가
    BigDecimal amount;
    // 부가세
    BigDecimal vat;
    // 주문 소계
    BigDecimal subtotalAmount;
    // 용기보증금 소계
    BigDecimal containerDeposit;
    // 공병보증금 소계
    BigDecimal bottleDeposit;
    // 주문 합계
    BigDecimal totalAmount;

    @Override
    public void refineValues() {

    }
}
