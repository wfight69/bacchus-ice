package com.davada.application.warehouse.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.warehouse.persistence.data.WarehouseData;
import com.davada.domain.product.condition.WarehouseCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class WarehouseRepositoryCustomImpl implements WarehouseRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<WarehouseData> findListByCondition(WarehouseCondition condition, int offset, int limit) {
        CriteriaQueryHelper<WarehouseData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, WarehouseData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(WarehouseCondition condition) {
        CriteriaQueryHelper<WarehouseData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, WarehouseData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<WarehouseData> criteriaBuilder, WarehouseCondition condition) {
        criteriaBuilder.addEqual("warehouseUuid", condition.getWarehouseUuid());
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("warehouseCode", condition.getWarehouseCode());
        criteriaBuilder.addLike("warehouseName", condition.getWarehouseName());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
