package com.davada.application.employee.service.port;

import com.davada.domain.wholesaler.condition.EmployeeCondition;
import com.davada.domain.wholesaler.entity.Employee;

import java.util.List;

public interface EmployeeQueryUseCase {

    List<Employee> retrieveListByCondition(EmployeeCondition condition, int offset, int limit);

    long countByCondition(EmployeeCondition condition);
}
