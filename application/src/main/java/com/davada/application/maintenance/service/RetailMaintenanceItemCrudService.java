package com.davada.application.maintenance.service;

import com.davada.application.maintenance.persistence.RetailMaintenanceItemPersistenceAdapter;
import com.davada.application.maintenance.service.port.RetailMaintenanceItemCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import com.davada.dto.maintenance.RetailMaintenanceItemDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.maintenance.error.RetailMaintenanceItemErrorCodes.MAINTENANCE_ITEM_1000;

@ApplicationScoped
@RequiredArgsConstructor
public class RetailMaintenanceItemCrudService implements RetailMaintenanceItemCrudUseCase {

    private final RetailMaintenanceItemPersistenceAdapter retailMaintenanceItemCrudPort;
    private final RetailMaintenanceItemDomainMapper retailMaintenanceItemDomainMapper;
    private final RetailMaintenanceItemDtoMapper retailMaintenanceItemDtoMapper;
    
    public IdValue createMaintenanceItem(RetailMaintenanceItemCrudUseCase.CreateMaintenanceItemCommand command) {
        String maintenanceItemUuid = ErpId.newId().getUuid().toString();
        RetailMaintenanceItem maintenanceItem = retailMaintenanceItemDomainMapper.toDomain(maintenanceItemUuid, command);
        maintenanceItem.refineValues();
        retailMaintenanceItemCrudPort.create(maintenanceItem);
        return new IdValue("maintenanceItemUuid", maintenanceItemUuid);
    }
    
    public RetailMaintenanceItemDto retrieveMaintenanceItem(String maintenanceItemUuid) {
        return retailMaintenanceItemCrudPort.retrieve(maintenanceItemUuid)
                .map(retailMaintenanceItemDtoMapper::toRetailMaintenanceItemDto)
                .orElseThrow(() -> new ErpRuntimeException(MAINTENANCE_ITEM_1000, maintenanceItemUuid));
    }
    
    public List<RetailMaintenanceItemDto> retrieveAllMaintenanceItem(String maintenanceUuid) {
        if (isEmpty(maintenanceUuid)) {
            throw new ErpEntityNotFoundException(maintenanceUuid);
        }
        return retailMaintenanceItemCrudPort.retrieveAllMaintenanceItem(maintenanceUuid)
                .stream()
                .map(retailMaintenanceItemDtoMapper::toRetailMaintenanceItemDto)
                .collect(Collectors.toList());
    }
    
    public BooleanValue updateMaintenanceItem(String maintenanceItemUuid, NameValuePairs nameValuePairs) {
        return retailMaintenanceItemCrudPort.retrieve(maintenanceItemUuid).map(maintenanceItem -> {
            boolean modified = retailMaintenanceItemCrudPort.update(maintenanceItemUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new MaintenanceItemRemovedEvent(maintenanceItem, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(maintenanceItemUuid));
    }
    
    public BooleanValue deleteMaintenanceItem(String maintenanceItemUuid, boolean permanent) {
        return retailMaintenanceItemCrudPort.retrieve(maintenanceItemUuid).map(maintenanceItem -> {
            boolean removed = retailMaintenanceItemCrudPort.delete(maintenanceItem, permanent);
            if (removed) {
                // domainEventPublisher.publish(new MaintenanceItemRemovedEvent(maintenanceItem, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(maintenanceItemUuid));
    }
}
