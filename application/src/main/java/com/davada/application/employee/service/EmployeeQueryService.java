package com.davada.application.employee.service;

import com.davada.application.employee.persistence.EmployeePersistenceAdapter;
import com.davada.application.employee.service.port.EmployeeQueryUseCase;
import com.davada.domain.wholesaler.condition.EmployeeCondition;
import com.davada.domain.wholesaler.entity.Employee;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class EmployeeQueryService implements EmployeeQueryUseCase {
    private final EmployeePersistenceAdapter employeeQueryPort;

    @Override
    public List<Employee> retrieveListByCondition(EmployeeCondition condition, int offset, int limit) {
        return employeeQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(EmployeeCondition condition) {
        return employeeQueryPort.countByCondition(condition);
    }
}
