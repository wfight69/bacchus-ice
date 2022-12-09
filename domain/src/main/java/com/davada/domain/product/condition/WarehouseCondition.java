package com.davada.domain.product.condition;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WarehouseCondition {
    String warehouseUuid;
    String wholesalerUuid;
    String warehouseCode;
    String warehouseName;
}