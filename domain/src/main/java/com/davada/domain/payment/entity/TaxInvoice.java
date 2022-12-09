package com.davada.domain.payment.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.util.DateHelper;
import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.IssueTaxStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaxInvoice extends AuditableEntity implements Refinable {
    // UUID
    String taxInvoiceUuid;
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    // 공급자 정보 O
    TaxInvoiceParty invoicerParty;
    // 공급받는자 정보 O
    TaxInvoiceParty invoiceeParty;
    // 수탁자 정보: 위수탁 세금계산서의 경우
    TaxInvoiceParty brokerParty;
    // 발급방향 O: 1 : 정발급 2 : 역발행
    int issueDirection;
    // 세금계산서 O: 1 : 세금계산서
    int taxInvoiceType;
    // 과세형태 O: 1 : 과세
    int taxType;
    //    int taxCalcType;
    // 영수/청구형태 O: 1 : 영수, 2 : 청구
    int purposeType;
    // 수정사유 코드: 수정세금계산서 작성 시만 필수
    String modifyCode;
    // 세금계산서 발행일자 O: YYYYMMDD 형식, 미입력시 기본값은 현재일자
    String writeDate;
    // 공급가액 O: 소수점 불가, 컴마(,)를 제외한 숫자만 입력
    String amountTotal;
    // 세액 O
    String taxTotal;
    // 합계금액 O
    String totalAmount;
    // 현금
    String cash;
    // 수표
    String chkBill;
    // 어음
    String note;
    // 외상미수금
    String credit;
    // 비고1
    String remark1;
    // 비고2
    String remark2;
    // 비고3
    String remark3;
    // 권 x
    String kwon;
    // 호 x
    String ho;
    // 일련번호 x
    String serialNum;
    // 품목 상세 목록 O
    List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItems;

    // 전자세금계산서 발급여부
    YN issueTaxYn;
    // 전자세금계산서 발행상태
    IssueTaxStatus issueTaxStatus;
    // 전자세금계산서 발급 결과코드
    Integer issueResult;
    // 전자세금계산서 발급 결과 오류코드
    String issueErrorMessage;
    // 생성일자
    String createDate;

    @Override
    public void refineValues() {
        issueDirection = 1;
        taxInvoiceType = 1;
        taxType = 1;
        purposeType = 1;
        issueTaxYn = YN.N;
        issueTaxStatus = IssueTaxStatus.PREPARING;
        issueResult = null;
        issueErrorMessage = null;
        createDate = DateHelper.currentDateString();
    }
}
