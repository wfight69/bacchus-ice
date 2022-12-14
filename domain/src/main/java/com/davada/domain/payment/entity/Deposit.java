package com.davada.domain.payment.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.VatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [입금] 유지관리 결재
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Deposit extends AuditableEntity implements Refinable {

    String depositUuid;
    String wholesalerUuid;
    // 소매점 UUID
    String retailShopUuid;
    // 업체코드
    String retailShopCode;
    // 업체명
    String retailShopName;
    String roadAddress;
    String roadAddressDetails;
    // 거래처 대표자 명
    String representativeName;
    // 거래처 대표자 모바일폰번호
    String representativeMobile;
    // 거래처 대표자 전화번호
    String representativeTelephone;

    // 출금담당자 UUID
    String employeeUuid;
    // 출금담당자 코드
    String employeeCode;
    // 출금담당자 명
    String employeeName;

    // 전표일자
    String createDate;
    // 입금일자
    String paymentDate;
    // 부가세유형
    VatType vatType;
    // 잔액
    Integer balanceAmount;
    // 출금은행
    String bankName;
    // 출금은행 계좌번호
    String bankAccount;
    // 계정과목
    CodeName accountSubject;

    // 전일마감채권
    Integer closeBondAmount;
    // 채권잔액
    Integer balanceBondAmount;

    // 공급가
    // 부가세
    // 소계
    // 보증금
    // 수수료
    // 매출일계

    // 용공 회수금액
    // 용공 수수료
    // 용공 일계

    // 카드 결재금액
    Integer cardPaymentAmount;
    // 현금 결재금액
    Integer cashPaymentAmount;
    // 통장 결재금액
    Integer accountPaymentAmount;
    // 용공회수 대체금액
    Integer offsettingAmount;
    // 총 결재금액
    Integer totalPaymentAmount;

    // 마감여부
    YN closeYn;
    // 비고
    String description;

    @Override
    public void refineValues() {

    }
}
