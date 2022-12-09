package com.davada.application.employee.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.common.vo.YN;
import com.davada.domain.wholesaler.entity.Employee;
import com.davada.domain.wholesaler.entity.EmploymentPeriod;
import com.davada.domain.wholesaler.entity.ErpUser;
import com.davada.domain.wholesaler.entity.PersonalDetails;
import com.davada.domain.wholesaler.vo.OccupationalCategory;
import com.davada.domain.wholesaler.vo.OfficeDuty;
import com.davada.domain.wholesaler.vo.OfficePosition;
import com.davada.domain.wholesaler.vo.VanTerm;
import com.davada.dto.wholesaler.EmployeeDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface EmployeeCrudUseCase {
    //
    IdValue createEmployee(Employee command);

    Employee retrieveEmployee(String employeeUuid);

    EmployeeDto retrieveEmployeeDto(String employeeUuid);

    EmployeeSimpleDto retrieveEmployeeSimpleDto(String employeeUuid);

    List<Employee> retrieveAllEmployee(String wholesalerUuid);

    BooleanValue updateEmployee(String employeeUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteEmployee(String employeeUuid, boolean permanent);

    IdValue createErpUser(String employeeUuid, ErpUser erpUser);

    BooleanValue updateErpUser(String employeeUuid, String erpUserUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteErpUser(String employeeUuid, String erpUserUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateEmployeeCommand {
        String wholesalerUuid;
        ErpUser erpUser;
        String employeeCode;
        String employeeName;
        CodeName salesCourse;
        String carNumber;
        OfficeDuty officeDuty;
        OccupationalCategory occupationalCategory;
        OfficePosition officePosition;
        CodeName department;
        String description;
        EmploymentPeriod employmentPeriod;
        PersonalDetails personalDetails;
        VanTerm vanTerm;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateErpUserCommand {
        String wholesalerUuid;
        String erpUserLoginId;
        String password;
        YN systemAdmin;
        YN systemLogin;
    }
}
