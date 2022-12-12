package com.davada.dto.maintenance;

import com.davada.domain.common.Entity;
import com.davada.domain.common.Refinable;
import lombok.*;

import java.math.BigDecimal;

/**
 * 소매점 관리내역(출고,수리,회수,오버홀) 품목 리스트
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailMaintenanceItemDto {
    // 유지관리 품목 UUID
    String maintenanceItemUuid;
    // 상품 UUID
    String productUuid;
    // 상품코드
    String productCode;
    // 상품명
    String productName;
    // 수량 개수
    Integer quantity;
    // 공급가
    BigDecimal amount;
    // 부가세
    BigDecimal vat;
    // 주문 소계
    BigDecimal subtotalAmount;
    // 주문 합계
    BigDecimal totalAmount;
}
