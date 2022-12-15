package com.davada.dto.purchase;

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
public class PurchaseOrderItemDto {
    String purchaseOrderItemUuid;
    String productUuid;
    String productName;
    String productCode;
    String volume;
    Integer bottlesInBox;
    UnitPrice containerPrice;
    Integer quantity;
    BigDecimal amount;
    BigDecimal itemVat;
    BigDecimal totalAmount;
}
