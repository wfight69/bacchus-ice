package com.davada.dto.wholesaler;

import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.Contact;
import com.davada.domain.wholesaler.entity.PersonalDetails;
import com.davada.domain.wholesaler.vo.OccupationalCategory;
import com.davada.domain.wholesaler.vo.OfficeDuty;
import com.davada.domain.wholesaler.vo.OfficePosition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeSimpleDto {
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
