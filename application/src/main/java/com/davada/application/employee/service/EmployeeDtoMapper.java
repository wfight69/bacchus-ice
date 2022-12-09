package com.davada.application.employee.service;

import com.davada.domain.common.vo.Contact;
import com.davada.domain.wholesaler.entity.Employee;
import com.davada.dto.wholesaler.EmployeeDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EmployeeDtoMapper {
    EmployeeDto toEmployeeDto(Employee employee, Contact wholesalerContact);

    EmployeeSimpleDto toEmployeeSimpleDto(Employee employee, Contact wholesalerContact);
}
