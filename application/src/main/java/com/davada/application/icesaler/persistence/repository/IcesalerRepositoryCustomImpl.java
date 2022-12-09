package com.davada.application.icesaler.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.icesaler.persistence.data.IcesalerData;
import com.davada.domain.icesaler.condition.IcesalerCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class IcesalerRepositoryCustomImpl implements IcesalerRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<IcesalerData> findListByCondition(IcesalerCondition condition, int offset, int limit) {
        CriteriaQueryHelper<IcesalerData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, IcesalerData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(IcesalerCondition condition) {
        CriteriaQueryHelper<IcesalerData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, IcesalerData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<IcesalerData> criteriaBuilder, IcesalerCondition condition) {
        criteriaBuilder.addEqual("icesalerCode", condition.getIcesalerCode());
        criteriaBuilder.addLike("icesalerName", condition.getIcesalerName());
        criteriaBuilder.addEqual("businessNumber", condition.getBusinessNumber());
        criteriaBuilder.addEqual("industryType", condition.getIndustryType());
        criteriaBuilder.addEqual("province", condition.getProvince());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
