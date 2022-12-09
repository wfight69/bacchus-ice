package com.davada.domain.common.vo;

import com.davada.domain.wholesaler.entity.PersonalDetails;
import com.davada.domain.wholesaler.vo.OccupationalCategory;
import com.davada.domain.wholesaler.vo.OfficeDuty;
import com.davada.domain.wholesaler.vo.OfficePosition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매입처 매입 관리 담당자 / 거래처 영업 관리 담당자
 * Drove by Employee
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeManager {
    String employeeUuid;
    String employeeCode;
    String employeeName;
    CodeName salesCourse;
    OfficeDuty officeDuty;
    OccupationalCategory occupationalCategory;
    OfficePosition officePosition;
    CodeName department;
    String description;
    Contact wholesalerContact;
    PersonalDetails personalDetails;
}
