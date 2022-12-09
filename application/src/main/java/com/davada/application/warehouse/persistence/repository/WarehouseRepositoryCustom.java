package com.davada.application.warehouse.persistence.repository;


import com.davada.application.warehouse.persistence.data.WarehouseData;
import com.davada.domain.product.condition.WarehouseCondition;

import java.util.List;

public interface WarehouseRepositoryCustom {
    List<WarehouseData> findListByCondition(WarehouseCondition condition, int offset, int limit);

    long countByCondition(WarehouseCondition condition);
}
