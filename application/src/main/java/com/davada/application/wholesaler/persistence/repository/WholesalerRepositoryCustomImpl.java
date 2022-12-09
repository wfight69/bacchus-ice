package com.davada.application.wholesaler.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.wholesaler.persistence.data.WholesalerData;
import com.davada.domain.wholesaler.condition.WholesalerCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class WholesalerRepositoryCustomImpl implements WholesalerRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<WholesalerData> findListByCondition(WholesalerCondition condition, int offset, int limit) {
        CriteriaQueryHelper<WholesalerData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, WholesalerData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(WholesalerCondition condition) {
        CriteriaQueryHelper<WholesalerData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, WholesalerData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<WholesalerData> criteriaBuilder, WholesalerCondition condition) {
        criteriaBuilder.addEqual("wholesalerCode", condition.getWholesalerCode());
        criteriaBuilder.addLike("wholesalerName", condition.getWholesalerName());
        criteriaBuilder.addEqual("businessNumber", condition.getBusinessNumber());
        criteriaBuilder.addEqual("industryType", condition.getIndustryType());
        criteriaBuilder.addEqual("province", condition.getProvince());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
