package com.davada.application.warehouse.service;

import com.davada.application.warehouse.persistence.WarehousePersistenceAdapter;
import com.davada.application.warehouse.service.port.WarehouseCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.product.entity.Warehouse;
import com.davada.domain.product.error.WarehouseErrorCodes;
import com.davada.dto.product.WarehouseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class WarehouseCrudService implements WarehouseCrudUseCase {

    private final WarehousePersistenceAdapter warehouseCrudPort;
    private final WarehouseDomainMapper warehouseDomainMapper;
    private final WarehouseDtoMapper warehouseDtoMapper;

    @Override
    public IdValue createWarehouse(Warehouse warehouse) {
        String warehouseUuid = ErpId.newId().getUuid().toString();
        //Warehouse warehouse = warehouseDomainMapper.toDomain(warehouseUuid, command);
        warehouse.setWarehouseUuid(warehouseUuid);
        warehouse.refineValues();

        warehouseCrudPort.create(warehouse);
        return new IdValue("warehouseUuid", warehouseUuid);
    }

    @Override
    public WarehouseDto retrieveWarehouse(String warehouseUuid) {
        Warehouse warehouse = warehouseCrudPort.retrieve(warehouseUuid)
                .orElseThrow(() -> new ErpRuntimeException(WarehouseErrorCodes.WAREHOUSE_1000, warehouseUuid));

        return warehouseDtoMapper.toWarehouseDto(warehouse);
    }

    @Override
    public List<WarehouseDto> retrieveAllWarehouse(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return warehouseCrudPort.retrieveAllWarehouse(wholesalerUuid)
                .stream()
                .map(warehouseDtoMapper::toWarehouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateWarehouse(String warehouseUuid, NameValuePairs nameValuePairs) {
        return warehouseCrudPort.retrieve(warehouseUuid).map(warehouse -> {
            boolean modified = warehouseCrudPort.update(warehouseUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new WarehouseRemovedEvent(warehouse, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(warehouseUuid));
    }

    @Override
    public BooleanValue deleteWarehouse(String warehouseUuid, boolean permanent) {
        return warehouseCrudPort.retrieve(warehouseUuid).map(warehouse -> {
            boolean removed = warehouseCrudPort.delete(warehouse, permanent);
            if (removed) {
                // domainEventPublisher.publish(new WarehouseRemovedEvent(warehouse, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(warehouseUuid));
    }
}
