package com.davada.application.payment.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.payment.persistence.data.PaymentData;
import com.davada.domain.payment.condition.PaymentCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class PaymentRepositoryCustomImpl implements PaymentRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<PaymentData> findListByCondition(PaymentCondition condition, int offset, int limit) {
        CriteriaQueryHelper<PaymentData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, PaymentData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(PaymentCondition condition) {
        CriteriaQueryHelper<PaymentData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, PaymentData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<PaymentData> criteriaBuilder, PaymentCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("supplierUuid", condition.getSupplierUuid());
        criteriaBuilder.addEqual("supplierCode", condition.getSupplierCode());
        criteriaBuilder.addLike("supplierName", condition.getSupplierName());
        criteriaBuilder.addBetween("createDate", condition.getStartCreateDate(), condition.getEndCreateDate());
        criteriaBuilder.addBetween("paymentDate", condition.getStartPaymentDate(), condition.getEndPaymentDate());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
