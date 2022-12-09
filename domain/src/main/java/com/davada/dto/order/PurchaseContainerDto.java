package com.davada.dto.order;

import com.davada.domain.order.entity.ContainerItem;
import com.davada.domain.order.vo.ContainerReturnType;
import com.davada.domain.order.vo.TransportType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 용기공병 반납
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseContainerDto {
    String purchaseContainerUuid;
    String wholesalerUuid;
    String supplierUuid;
    String supplierCode;
    String supplierName;
    String supplierRepresentativeName;
    String businessNumber;
    ContainerReturnType containerReturnType;
    String returnDate;
    String warehouseUuid;
    String warehouseCode;
    String warehouseName;
    TransportType transportType;
    String description;
    Integer containerBottleCount;
    Integer containerCount;
    Integer bottleCount;
    BigDecimal containerDepositAmount;
    BigDecimal containerFeeAmount;
    BigDecimal containerFeeVatAmount;
    BigDecimal containerSubtotalAmount;
    BigDecimal bottlesDepositAmount;
    BigDecimal bottlesFeeAmount;
    BigDecimal bottlesFeeVatAmount;
    BigDecimal bottlesSubtotalAmount;
    BigDecimal totalAmount;

    Set<ContainerItem> containerItems = new LinkedHashSet<>();
}
