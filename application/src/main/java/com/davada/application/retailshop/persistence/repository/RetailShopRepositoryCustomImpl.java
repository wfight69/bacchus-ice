package com.davada.application.retailshop.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.retailshop.persistence.data.RetailShopData;
import com.davada.domain.retailshop.condition.RetailShopCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailShopRepositoryCustomImpl implements RetailShopRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<RetailShopData> findListByCondition(RetailShopCondition condition, int offset, int limit) {
        CriteriaQueryHelper<RetailShopData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, RetailShopData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(RetailShopCondition condition) {
        CriteriaQueryHelper<RetailShopData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, RetailShopData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<RetailShopData> criteriaBuilder, RetailShopCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addLike("retailShopName", condition.getRetailShopName());
        criteriaBuilder.addLike("representativeName", condition.getRepresentativeName());
        criteriaBuilder.addLike("mobilePhoneNumber", condition.getMobilePhoneNumber());
        criteriaBuilder.addEqual("salesPersonCode", "code", condition.getSalesPersonCode());
        criteriaBuilder.addEqual("province", condition.getProvince());
        criteriaBuilder.addEqual("retailShopStatus", condition.getRetailShopStatus());
        criteriaBuilder.addEqual("retailShopCode", condition.getRetailShopCode());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
