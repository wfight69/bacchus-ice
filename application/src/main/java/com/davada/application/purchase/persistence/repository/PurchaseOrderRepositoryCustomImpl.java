package com.davada.application.purchase.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.purchase.persistence.data.PurchaseOrderData;
import com.davada.domain.purchase.condition.PurchaseOrderCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class PurchaseOrderRepositoryCustomImpl implements PurchaseOrderRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<PurchaseOrderData> findListByCondition(PurchaseOrderCondition condition, int offset, int limit) {
        CriteriaQueryHelper<PurchaseOrderData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, PurchaseOrderData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(PurchaseOrderCondition condition) {
        CriteriaQueryHelper<PurchaseOrderData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, PurchaseOrderData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<PurchaseOrderData> criteriaBuilder, PurchaseOrderCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("purchaseType", condition.getPurchaseType());
        criteriaBuilder.addBetween("orderDate", condition.getStartOrderDate(), condition.getEndOrderDate());
        criteriaBuilder.addEqual("supplierCode", condition.getSupplierCode());
        criteriaBuilder.addLike("supplierName", condition.getSupplierName());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
