package com.davada.application.maintenance.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import com.davada.domain.common.vo.YN;
import com.davada.domain.maintenance.condition.RetailMaintenanceCondition;
import com.davada.domain.maintenance.vo.RetailMaintenanceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailMaintenanceRepositoryCustomImpl implements RetailMaintenanceRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<RetailMaintenanceData> findListByCondition(RetailMaintenanceCondition condition, int offset, int limit) {
        CriteriaQueryHelper<RetailMaintenanceData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, RetailMaintenanceData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(RetailMaintenanceCondition condition) {
        CriteriaQueryHelper<RetailMaintenanceData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, RetailMaintenanceData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<RetailMaintenanceData> criteriaBuilder, RetailMaintenanceCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("maintenanceType", condition.getMaintenanceType());
        criteriaBuilder.addEqual("employeeUuid", condition.getEmployeeUuid());
        //criteriaBuilder.addBetween("orderDate", condition.getStartMaintenanceDate(), condition.getEndMaintenanceDate());
        //criteriaBuilder.addBetween("orderCreateDate", condition.getStartMaintenanceDate(), condition.getEndMaintenanceDate());
        criteriaBuilder.addLike("employeeName", condition.getSalesManagerName());
        criteriaBuilder.addEqual("retailShopCode", condition.getRetailShopCode());
        criteriaBuilder.addLike("retailShopName", condition.getRetailShopName());
        criteriaBuilder.addEqual("registerMaintenanceYn", YN.Y);
        criteriaBuilder.addIn("retailMaintenanceStatus",
                RetailMaintenanceStatus.RECEIVED,
                RetailMaintenanceStatus.ACCEPTED,
                RetailMaintenanceStatus.RELEASING,
                RetailMaintenanceStatus.RELEASED,
                RetailMaintenanceStatus.DELIVERED);
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
