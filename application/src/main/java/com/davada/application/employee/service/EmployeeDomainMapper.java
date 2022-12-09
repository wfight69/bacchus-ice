package com.davada.application.employee.service;

import com.davada.domain.wholesaler.entity.Employee;
import com.davada.domain.wholesaler.entity.ErpUser;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EmployeeDomainMapper {
    //@Mapping(target = "auditLog", ignore = true)
    //Employee toDomain(String employeeUuid, EmployeeCrudUseCase.CreateEmployeeCommand command);

    //ErpUser toDomain(String erpUserUuid, EmployeeCrudUseCase.CreateErpUserCommand command);
}
