package com.davada.dto.product;

import com.davada.domain.product.vo.WarehouseType;
import com.davada.domain.wholesaler.vo.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WarehouseDto {
    String warehouseUuid;
    String wholesalerUuid;
    String warehouseCode;
    String warehouseName;
    Location location;
    String telephone;
    String employeeUuid;
    String employeeName;
    String supplierUuid;
    String supplierName;
    WarehouseType warehouseType;
    String description;
}