package com.davada.domain.icesaler.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.vo.CompanyType;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.common.vo.IndustryType;
import com.davada.domain.icesaler.vo.IcesalerStatus;
import com.davada.domain.wholesaler.vo.Location;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Icesaler extends AuditableEntity {
    // 냉장업체 UUID
    String icesalerUuid;
    // 업체코드
    @NotBlank(message = "냉장업체코드는 필수 입력입니다.")
    String icesalerCode;
    // 업체명
    String icesalerName;
    // 회사 주소
    Location location;
    // 사업자번호
    String businessNumber;
    // 법인번호
    String corporationNumber;
    // 업태
    String uptae;
    // 종목
    String jongmok;
    // (법인 개인)
    CompanyType companyType;
    // 업종 유형(주류, 제조사, 냉동_냉장)
    IndustryType industryType;
    // 회사 연락처: 이름, 전화, 팩스, 이메일
    Contact companyContact;
    // 대표자 연락처
    Contact ceoContact;
    // 담당자 연락처
    Contact managerContact;
    // 서비스시작일자
    String serviceStartDate;
    // 서비스 월정 금액
    Integer serviceMonthlyAmount;
    // 서비스상태
    IcesalerStatus icesalerStatus;
    // 비고
    String remarks;
}
