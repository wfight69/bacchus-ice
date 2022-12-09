package com.davada.application.supplier.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.supplier.persistence.data.SupplierData;
import com.davada.domain.supplier.condition.SupplierCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class SupplierRepositoryCustomImpl implements SupplierRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<SupplierData> findListByCondition(SupplierCondition condition, int offset, int limit) {
        CriteriaQueryHelper<SupplierData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, SupplierData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(SupplierCondition condition) {
        CriteriaQueryHelper<SupplierData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, SupplierData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<SupplierData> criteriaBuilder, SupplierCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("province", condition.getProvince());
        criteriaBuilder.addEqual("substitutionYn", condition.getSubstitutionYn());
        criteriaBuilder.addEqual("supplierCode", condition.getSupplierCode());
        criteriaBuilder.addLike("supplierName", condition.getSupplierName());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
