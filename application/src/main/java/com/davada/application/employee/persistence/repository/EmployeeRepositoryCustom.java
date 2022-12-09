package com.davada.application.employee.persistence.repository;

import com.davada.application.employee.persistence.data.EmployeeData;
import com.davada.domain.wholesaler.condition.EmployeeCondition;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<EmployeeData> findListByCondition(EmployeeCondition condition, int offset, int limit);

    long countByCondition(EmployeeCondition condition);

}
