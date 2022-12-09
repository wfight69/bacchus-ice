package com.davada.application.warehouse.service.port;


import com.davada.domain.product.condition.WarehouseCondition;
import com.davada.domain.product.entity.Warehouse;

import java.util.List;

public interface WarehouseQueryUseCase {

    List<Warehouse> retrieveListByCondition(WarehouseCondition condition, int offset, int limit);

    long countByCondition(WarehouseCondition condition);
}
