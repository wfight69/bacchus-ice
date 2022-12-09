package com.davada.dto.wholesaler;

import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.wholesaler.entity.EmploymentPeriod;
import com.davada.domain.wholesaler.entity.ErpUser;
import com.davada.domain.wholesaler.entity.PersonalDetails;
import com.davada.domain.wholesaler.vo.OccupationalCategory;
import com.davada.domain.wholesaler.vo.OfficeDuty;
import com.davada.domain.wholesaler.vo.OfficePosition;
import com.davada.domain.wholesaler.vo.VanTerm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeDto {
    // Employee UUID
    String employeeUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    // Login User
    ErpUser erpUser;
    // 사원코드
    String employeeCode;
    // 사원명
    String employeeName;
    // 담당코스
    CodeName salesCourse;
    // 차량번호
    String carNumber;
    // 직무
    OfficeDuty officeDuty;
    // 직종
    OccupationalCategory occupationalCategory;
    // 직위
    OfficePosition officePosition;
    // 현소속부서
    CodeName department;
    // 기타(비고)
    String description;
    // 회사 연락처
    Contact wholesalerContact;
    // 입사일자, 퇴사일자
    EmploymentPeriod employmentPeriod;
    // 개인정보
    PersonalDetails personalDetails;
    // 단말정보
    VanTerm vanTerm;
}
