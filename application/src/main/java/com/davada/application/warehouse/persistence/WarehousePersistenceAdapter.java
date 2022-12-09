package com.davada.application.warehouse.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.warehouse.persistence.repository.WarehouseRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.product.condition.WarehouseCondition;
import com.davada.domain.product.entity.Warehouse;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class WarehousePersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final WarehouseDataMapper jpaMapper;
    private final WarehouseRepository warehouseRepository;

    public void create(final Warehouse warehouse) {
        warehouse.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        warehouseRepository.save(jpaMapper.toWarehouseData(warehouse));
    }

    public Optional<Warehouse> retrieve(String warehouseUuid) {
        return warehouseRepository.findById(warehouseUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(entity -> Optional.of(jpaMapper.fromWarehouseData(entity)))
                .orElse(Optional.empty());
    }

    public List<Warehouse> retrieveAllWarehouse(String warehouseUuid) {
        return warehouseRepository.findByWarehouseUuidAndAuditLog_Deleted(warehouseUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromWarehouseData).collect(Collectors.toList());
    }

    public boolean update(String warehouseUuid, NameValuePairs nameValuePairs) {
        return warehouseRepository.findById(warehouseUuid)
                .map(WarehouseData -> {
                    WarehouseData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = WarehouseData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        warehouseRepository.save(WarehouseData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(Warehouse warehouse, boolean permanent) {
        return warehouseRepository.findById(warehouse.getWarehouseUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(WarehouseData -> {
                    if (permanent) {
                        warehouseRepository.delete(WarehouseData);
                    } else {
                        WarehouseData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        warehouseRepository.save(WarehouseData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<Warehouse> retrieveListByCondition(WarehouseCondition condition, int offset, int limit) {
        return warehouseRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromWarehouseData)
                .collect(Collectors.toList());
    }
    
    public long countByCondition(WarehouseCondition condition) {
        return warehouseRepository.countByCondition(condition);
    }
}
