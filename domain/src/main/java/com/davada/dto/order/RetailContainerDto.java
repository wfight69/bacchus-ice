package com.davada.dto.order;

import com.davada.domain.common.vo.CodeName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 용기공병 회수
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailContainerDto {
    String retailContainerUuid;
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    String employeeUuid;
    String employeeName;
    String employeeCode;
    CodeName salesCourse;
    ContainerCollectType containerCollectType;
    String collectDate;
    String warehouseUuid;
    String warehouseCode;
    String warehouseName;
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
