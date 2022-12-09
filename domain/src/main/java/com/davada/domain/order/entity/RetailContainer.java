package com.davada.domain.order.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.order.vo.ContainerCollectType;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 용기공병 회수
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailContainer extends AuditableEntity implements Refinable {
    // 용기공병 회수 UUID
    String retailContainerUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    // 소매점 UUID
    String retailShopUuid;
    // 소매점 코드
    String retailShopCode;
    // 소매점 상호명
    String retailShopName;
    // 판매담당자 UUID
    String employeeUuid;
    // 판매담당자 이름
    String employeeName;
    // 판매담당자 코드
    String employeeCode;
    // 판매담당자 코스
    CodeName salesCourse;
    // 회수 유형
    ContainerCollectType containerCollectType;
    // 회수 일자
    String collectDate;
    // 창고
    String warehouseUuid;
    // 창고코드
    String warehouseCode;
    // 창고명
    String warehouseName;
    // 비고
    String description;

    // 입용기
    Integer containerBottleCount;
    // 공용기
    Integer containerCount;
    // 공병
    Integer bottleCount;

    // 용기 보증금
    BigDecimal containerDepositAmount;
    // 용기 수수료
    BigDecimal containerFeeAmount;
    // 용기 수수료 부가가치세
    BigDecimal containerFeeVatAmount;
    // 용기 소계
    BigDecimal containerSubtotalAmount;

    // 공병 보증금
    BigDecimal bottlesDepositAmount;
    // 공병 수수료
    BigDecimal bottlesFeeAmount;
    // 공병 수수료 부가가치세
    BigDecimal bottlesFeeVatAmount;
    // 공병 소계
    BigDecimal bottlesSubtotalAmount;

    // 합계
    BigDecimal totalAmount;

    // 용기공병 회수 품목
    Set<ContainerItem> containerItems = new LinkedHashSet<>();

    @Override
    public void refineValues() {

    }
}
