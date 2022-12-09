package com.davada.domain.order.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.YN;
import com.davada.domain.order.vo.CalculateStatus;
import com.davada.domain.order.vo.PurchaseType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 구매 주문
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseOrder extends AuditableEntity implements Refinable {
    // 매입 주문 UUID
    String purchaseOrderUuid;
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
    // 창고
    String warehouseUuid;
    String warehouseCode;
    // 창고명
    String warehouseName;
    // 거래명세서 출력 여부
    YN tradingStatementYn;
    // 부가세 적용여부
    YN vatYn;
    // 마감여부 미마감/마감
    CalculateStatus calculateStatus;
    // 매입유형
    PurchaseType purchaseType;
    // 매입 일자
    String orderDate;
    // 매입 시간
    String orderTime;
    // 품목명외
    String productShortName;
    // 박스
    Integer containerQuantity;
    // 본
    Integer bottleQuantity;
    // 보충 (무료 박스 개수)
    Integer supplementQuantity;
    // 공급가
    BigDecimal amount = BigDecimal.ZERO;
    // 부가세
    BigDecimal vat = BigDecimal.ZERO;
    // 소계
    BigDecimal subtotalAmount = BigDecimal.ZERO;
    // 용기보증금
    BigDecimal containerDeposit = BigDecimal.ZERO;
    // 공병보증금
    BigDecimal bottleDeposit = BigDecimal.ZERO;
    // 합계
    BigDecimal totalAmount = BigDecimal.ZERO;
    // 비고
    String description;
    // 주문상품
    Set<PurchaseOrderItem> purchaseOrderItems = new LinkedHashSet<>();

    @Override
    public void refineValues() {
        if (tradingStatementYn == null) {
            tradingStatementYn = YN.N;
        }
        if (vatYn == null) {
            vatYn = YN.N;
        }
    }
}