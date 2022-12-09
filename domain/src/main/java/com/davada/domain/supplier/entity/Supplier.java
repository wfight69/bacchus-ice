package com.davada.domain.supplier.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.*;
import com.davada.domain.wholesaler.vo.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Supplier extends AuditableEntity implements Refinable {
    // 주류제조사 UUID
    String supplierUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    // 주류제조사 코드
    String supplierCode;
    // 주류제조사 상호명
    String supplierName;
    // 주류제조사 사업자등록번호
    String businessNumber;

    //폰번호 for SMS service
    String mobilePhoneNumber;
    // 주소, 우편번호, 지역구분
    Location location;
    //업체구분
    CompanyType companyType;
    //업체유형
    IndustryType industryType;
    //대표전화, 팩스번호, 대표자메일
    Contact contact;

    // 공급자 대표자, 담당자 연락처 추가
    Contact reprsContact; // FIXME: representativeContact
    // 공급자 담당자, 담당자 연락처 추가
    Contact chargeContact;

    // 업태
    String uptae;
    // 종목
    String jongmok;

    // 매입담당자 UUID
    String employeeUuid;
    // 용공대체여부
    YN substitutionYn;
    //진행구분
    ShopStatus supplierStatus;
    //기타(비고)
    String description;

    @Override
    public void refineValues() {

    }
}
