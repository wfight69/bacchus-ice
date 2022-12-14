package com.davada.application.payment.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.payment.persistence.data.DepositData;
import com.davada.domain.payment.condition.DepositCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class DepositRepositoryCustomImpl implements DepositRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<DepositData> findListByCondition(DepositCondition condition, int offset, int limit) {
        CriteriaQueryHelper<DepositData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, DepositData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(DepositCondition condition) {
        CriteriaQueryHelper<DepositData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, DepositData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<DepositData> criteriaBuilder, DepositCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("retailShopUuid", condition.getRetailShopUuid());
        criteriaBuilder.addEqual("retailShopCode", condition.getRetailShopCode());
        criteriaBuilder.addLike("retailShopName", condition.getRetailShopName());
        criteriaBuilder.addEqual("employeeCode", condition.getEmployeeCode());
        criteriaBuilder.addLike("employeeName", condition.getEmployeeName());
        criteriaBuilder.addBetween("createDate", condition.getStartCreateDate(), condition.getEndCreateDate());
        criteriaBuilder.addBetween("paymentDate", condition.getStartPaymentDate(), condition.getEndPaymentDate());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
