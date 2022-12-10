package com.davada.application.code.persistence.repository;

import com.davada.application.code.persistence.data.ErpCodeData;
import com.davada.application.common.CriteriaQueryHelper;
import com.davada.domain.code.condition.ErpCodeCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodeRepositoryCustomImpl implements ErpCodeRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<ErpCodeData> findListByCondition(ErpCodeCondition condition, int offset, int limit) {
        CriteriaQueryHelper<ErpCodeData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, ErpCodeData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(ErpCodeCondition condition) {
        CriteriaQueryHelper<ErpCodeData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, ErpCodeData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<ErpCodeData> criteriaBuilder, ErpCodeCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("codeSetUuid", condition.getCodeSetUuid());
        criteriaBuilder.addEqual("codeSetName", condition.getCodeSetName());
        criteriaBuilder.addEqual("name", condition.getName());
        criteriaBuilder.addLike("nameKeyword", condition.getNameKeyword());
        criteriaBuilder.addLike("label", condition.getLabel());
        criteriaBuilder.addEqual("refCode", condition.getRefCode());
        criteriaBuilder.addEqual("enabled", condition.getEnabled());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
