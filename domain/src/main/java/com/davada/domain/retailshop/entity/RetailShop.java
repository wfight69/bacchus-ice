package com.davada.domain.retailshop.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.entity.ChargeDetails;
import com.davada.domain.common.vo.*;
import com.davada.domain.retailshop.vo.ServicePeriod;
import com.davada.domain.wholesaler.vo.Location;
import lombok.*;

/**
 * 소매점
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailShop extends AuditableEntity implements Refinable {
    //소매점 UUID
    String retailShopUuid;
    //주류도매업체 UUID
    String wholesalerUuid;
    //납품처코드
    String retailShopCode;
    //상호명
    String retailShopName;
    //사업자등록번호
    String businessNumber;
    //대표자명(삭제)
    //String representativeName;
    //폰번호 for SMS service
    String mobilePhoneNumber;
    //주소, 우편번호, 지역구분
    Location location;
    //업체구분
    CompanyType companyType;
    //업체유형
    IndustryType industryType;
    //채권한도
    Long bondLimitAmount;
    //전일채권
    // Long currentBondAmount;
    //회사(명, 전화번호, 팩스, 이메일)
    Contact companyContact;
    //소매점대표자(명, 전화번호, 팩스, 이메일)
    Contact ceoContact;
    //소매점담당자(명, 전화번호, 팩스, 이메일)
    Contact managerContact;
    //라이센스번호
    String licenseNo;
    // 업태
    String uptae;
    // 종목
    String jongmok;
    //영업담당자 UUID
    String employeeUuid;
    //업종구분
    BusinessCategory businessCategory;
    //용공보증금여부
    YN containerDeposit;
    //진행구분
    ShopStatus retailShopStatus;
    //거래시작, 종료일자
    ServicePeriod servicePeriod;
    //기타(비고)
    String description;
    //=> 전자세금계산서 담당자 정보
    ChargeDetails chargeDetails;

    @Override
    public void refineValues() {

    }


//=> 약정서외 내역 설정
    //  파일명
    //  확장자
//=> 품목별 단가
//=> 담당자 이력
//=> 주문번호 목록
//=> 대여금
//=> 지원 품목
}
