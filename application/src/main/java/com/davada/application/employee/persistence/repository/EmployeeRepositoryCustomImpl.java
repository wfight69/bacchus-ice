package com.davada.application.employee.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.employee.persistence.data.EmployeeData;
import com.davada.domain.wholesaler.condition.EmployeeCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<EmployeeData> findListByCondition(EmployeeCondition condition, int offset, int limit) {
        CriteriaQueryHelper<EmployeeData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, EmployeeData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(EmployeeCondition condition) {
        CriteriaQueryHelper<EmployeeData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, EmployeeData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<EmployeeData> criteriaBuilder, EmployeeCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addLike("employeeName", condition.getEmployeeName());
        criteriaBuilder.addEqual("employeeCode", condition.getEmployeeCode());
        if (condition.isResign()) {
            criteriaBuilder.isNotNull("employmentPeriod", "leavingDate");
        } else {
            criteriaBuilder.isNull("employmentPeriod", "leavingDate");
        }
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
