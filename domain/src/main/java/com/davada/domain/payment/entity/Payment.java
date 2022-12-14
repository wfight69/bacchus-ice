package com.davada.domain.payment.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.VatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [출금] 구매상품 지급 결재
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends AuditableEntity implements Refinable {

    String paymentUuid;
    String wholesalerUuid;
    // 주류제조사 UUID
    String supplierUuid;
    // 주류제조사 코드
    String supplierCode;
    // 주류제조사 상호명
    String supplierName;

    String roadAddress;
    String roadAddressDetails;
    // 거래처 대표자 명
    String representativeName;
    // 거래처 대표자 모바일폰번호
    String representativeMobile;
    // 거래처 대표자 전화번호
    String representativeTelephone;

    // 전표일자
    String createDate;
    // 출금일자
    String paymentDate;
    // 부가세유형
    VatType vatType;
    // 전일마감채무
    Integer closeBondAmount;
    // 채무잔액
    Integer balanceBondAmount;
    // 출금액
    Integer paymentAmount;
    // 용기대체
    Integer containerOffsetAmount;
    // 총출금액
    Integer totalPaymentAmount;
    // 출금은행명
    String bankName;
    // 계좌번호
    String bankAccount;

    // 마감여부
    YN closeYn;
    // 비고
    String description;

    @Override
    public void refineValues() {

    }
}
