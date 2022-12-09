package com.davada.application.employee.persistence;

import com.davada.application.common.persistence.data.CodeNameData;
import com.davada.application.employee.persistence.data.EmployeeData;
import com.davada.application.employee.persistence.data.EmploymentPeriodData;
import com.davada.application.employee.persistence.data.ErpUserData;
import com.davada.application.employee.persistence.data.PersonalDetailsData;
import com.davada.application.employee.persistence.data.VanTermData;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.wholesaler.entity.Employee;
import com.davada.domain.wholesaler.entity.EmploymentPeriod;
import com.davada.domain.wholesaler.entity.ErpUser;
import com.davada.domain.wholesaler.entity.PersonalDetails;
import com.davada.domain.wholesaler.vo.VanTerm;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EmployeeDataMapper {
    @Mapping(target = "version", ignore = true)
    EmployeeData toEmployeeData(Employee employee);

    @InheritInverseConfiguration
    Employee fromEmployeeData(EmployeeData employeeData);

    //    @Mapping(target = "auditLog", ignore = true)
    ErpUserData toErpUserData(ErpUser erpUser);

    ErpUser fromErpUserData(ErpUserData erpUserData);

    EmploymentPeriodData toEmploymentPeriodData(EmploymentPeriod employmentPeriod);

    PersonalDetailsData toPersonalDetailsData(PersonalDetails personalDetails);

    CodeNameData toCodeNameData(CodeName codeName);

    VanTermData toVanTermData(VanTerm vanTerm);
}
