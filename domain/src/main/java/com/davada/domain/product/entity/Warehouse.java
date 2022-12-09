package com.davada.domain.product.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.product.vo.WarehouseType;
import com.davada.domain.wholesaler.vo.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Warehouse extends AuditableEntity implements Refinable {
    // UUID
    String warehouseUuid;
    // 도매점 UUID
    String wholesalerUuid;
    // 창고코드
    String warehouseCode;
    // 창고명
    String warehouseName;
    // 주소
    Location location;
    // 전화번호
    String telephone;
    // 창고관리자
    String employeeUuid;
    // 창고관리자명
    String employeeName;
    // 매출거래처
    String supplierUuid;
    // 매출거래처명
    String supplierName;
    // 창고구분
    WarehouseType warehouseType;
    // 비고
    String description;

    @Override
    public void refineValues() {

    }
}