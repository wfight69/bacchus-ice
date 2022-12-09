package com.davada.domain.order.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.order.vo.ContainerReturnType;
import com.davada.domain.order.vo.TransportType;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 용기공병 반납
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseContainer extends AuditableEntity implements Refinable {
    // 용기공병 반납 UUID
    String purchaseContainerUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    // 주류제조사 UUID
    String supplierUuid;
    // 주류제조사 코드
    String supplierCode;
    // 주류제조사 상호명
    String supplierName;
    // 주류제조사 대표자
    String supplierRepresentativeName;
    // 주류제조사 사업자등록번호
    String businessNumber;
    // 반납 유형
    ContainerReturnType containerReturnType;
    // 반납 일자
    String returnDate;
    // 창고
    String warehouseUuid;
    // 창고코드
    String warehouseCode;
    // 창고명
    String warehouseName;
    // 운송구분
    TransportType transportType;
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


    // 용기공병 반납 품목
    Set<ContainerItem> containerItems = new LinkedHashSet<>();

    @Override
    public void refineValues() {

    }
}
