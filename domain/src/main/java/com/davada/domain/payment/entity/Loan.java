package com.davada.domain.payment.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.payment.vo.VatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 대여금
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan extends AuditableEntity implements Refinable {
    // 대여금 UUID
    String loanUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    // 거래처 UUID
    String retailShopUuid;
    // 거래처 코드
    String retailShopCode;
    // 거래처 상호명
    String retailShopName;
    // 거래처 전화번호
    String retailShopTelephone;
    // 거래처 주소
    String roadAddress;
    String roadAddressDetails;
    // 거래처 대표자 명
    String representativeName;
    // 거래처 대표자 전화번호
    String representativeTelephone;

    // 출금담당자 UUID
    String employeeUuid;
    // 출금담당자 코드
    String employeeCode;
    // 출금담당자 명
    String employeeName;

    // 부가세유형(세금계산서, 거래명세서, 현금영수증, 기타)
    VatType vatType;
    // 전표일자
    String createDate;
    // 대여일자
    String loanDate;
    // 대여금액
    Integer loanAmount;
    // 대여금 수수료
    Integer loanFee;
    // 분할회차
    Integer installmentPlan;
    // 상환금액
    Integer repaymentAmount;
    // 잔액
    Integer balanceAmount;
    // 출금은행
    String bankName;
    // 출금은행 계좌번호
    String bankAccount;
    // 적용
    String description;

    @Override
    public void refineValues() {
        loanAmount = loanAmount == null ? 0 : loanAmount;
        loanFee = loanFee == null ? 0 : loanFee;
        installmentPlan = installmentPlan == null ? 0 : installmentPlan;
        repaymentAmount = repaymentAmount == null ? 0 : repaymentAmount;
        balanceAmount = loanAmount + loanFee;
    }
}
