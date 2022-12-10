package com.davada.application.code.persistence.repository;

import com.davada.application.code.persistence.data.ErpCodeSetData;
import com.davada.application.common.CriteriaQueryHelper;
import com.davada.domain.code.condition.ErpCodeSetCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodeSetRepositoryCustomImpl implements ErpCodeSetRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<ErpCodeSetData> findListByCondition(ErpCodeSetCondition condition, int offset, int limit) {
        CriteriaQueryHelper<ErpCodeSetData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, ErpCodeSetData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(ErpCodeSetCondition condition) {
        CriteriaQueryHelper<ErpCodeSetData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, ErpCodeSetData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<ErpCodeSetData> criteriaBuilder, ErpCodeSetCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("name", condition.getName());
        criteriaBuilder.addLike("nameKeyword", condition.getNameKeyword());
        criteriaBuilder.addLike("label", condition.getLabel());
        criteriaBuilder.addEqual("erpCodeSetType", condition.getErpCodeSetType());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
