package com.davada.application.maintenance.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import com.davada.domain.maintenance.condition.RetailRequestMaintenanceCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailRequestMaintenanceRepositoryCustomImpl implements RetailRequestMaintenanceRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<RetailMaintenanceData> findListByCondition(RetailRequestMaintenanceCondition condition, int offset, int limit) {
        CriteriaQueryHelper<RetailMaintenanceData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, RetailMaintenanceData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(RetailRequestMaintenanceCondition condition) {
        CriteriaQueryHelper<RetailMaintenanceData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, RetailMaintenanceData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<RetailMaintenanceData> criteriaBuilder, RetailRequestMaintenanceCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("retailMaintenanceChannel", condition.getRetailMaintenanceChannel());
        //criteriaBuilder.addEqual("requestMaintenanceStatus", condition.getRetailRequestMaintenanceStatus());
        criteriaBuilder.addBetween("orderDate", condition.getStartMaintenanceDate(), condition.getEndMaintenanceDate());
        criteriaBuilder.addEqual("salesCourse", "code", condition.getSalesCourseCode());
        criteriaBuilder.addLike("employeeName", condition.getSalesManagerName());
        criteriaBuilder.addEqual("retailShopCode", condition.getRetailShopCode());
        criteriaBuilder.addLike("retailShopName", condition.getRetailShopName());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
