package com.davada.dto.order;

import com.davada.domain.common.vo.YN;
import com.davada.domain.order.vo.CalculateStatus;
import com.davada.domain.order.vo.PurchaseType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

/**
 * 구매 주문
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseOrderDto {
    String purchaseOrderUuid;
    String wholesalerUuid;
    String supplierUuid;
    String supplierCode;
    String supplierName;
    String supplierRepresentativeName;
    String businessNumber;
    String warehouseUuid;
    String warehouseCode;
    String warehouseName;
    YN tradingStatementYn;
    YN vatYn;
    CalculateStatus calculateStatus;
    PurchaseType purchaseType;
    String orderDate;
    String orderTime;
    String productShortName;
    Integer containerQuantity;
    Integer bottleQuantity;
    Integer supplementQuantity;
    BigDecimal amount = BigDecimal.ZERO;
    BigDecimal vat = BigDecimal.ZERO;
    BigDecimal subtotalAmount = BigDecimal.ZERO;
    BigDecimal containerDeposit = BigDecimal.ZERO;
    BigDecimal bottleDeposit = BigDecimal.ZERO;
    BigDecimal totalAmount = BigDecimal.ZERO;
    String description;
    Set<PurchaseOrderItemDto> purchaseOrderItems;
}