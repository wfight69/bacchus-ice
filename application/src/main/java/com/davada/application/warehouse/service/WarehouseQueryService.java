package com.davada.application.warehouse.service;

import com.davada.application.warehouse.persistence.WarehousePersistenceAdapter;
import com.davada.application.warehouse.service.port.WarehouseQueryUseCase;
import com.davada.domain.product.condition.WarehouseCondition;
import com.davada.domain.product.entity.Warehouse;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class WarehouseQueryService implements WarehouseQueryUseCase {
    private final WarehousePersistenceAdapter warehouseQueryPort;

    @Override
    public List<Warehouse> retrieveListByCondition(WarehouseCondition condition, int offset, int limit) {
        return warehouseQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(WarehouseCondition condition) {
        return warehouseQueryPort.countByCondition(condition);
    }
}
