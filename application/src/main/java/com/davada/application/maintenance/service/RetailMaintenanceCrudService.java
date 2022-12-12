package com.davada.application.maintenance.service;

import com.davada.application.maintenance.persistence.RetailMaintenancePersistenceAdapter;
import com.davada.application.maintenance.service.port.RetailMaintenanceCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import com.davada.dto.maintenance.RetailMaintenanceDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.maintenance.error.RetailMaintenanceErrorCodes.MAINTENANCE_1000;

@ApplicationScoped
@RequiredArgsConstructor
public class RetailMaintenanceCrudService implements RetailMaintenanceCrudUseCase {

    private final RetailMaintenancePersistenceAdapter retailMaintenanceCrudPort;
    private final RetailMaintenanceDomainMapper retailMaintenanceDomainMapper;
    private final RetailMaintenanceDtoMapper retailMaintenanceDtoMapper;

    @Override
    public IdValue createMaintenance(RetailMaintenanceCrudUseCase.CreateMaintenanceCommand command) {
        String maintenanceUuid = ErpId.newId().getUuid().toString();
        RetailMaintenance maintenance = retailMaintenanceDomainMapper.toDomain(maintenanceUuid, command);
        maintenance.refineValues();
        maintenance.registerMaintenanceStatus();
        retailMaintenanceCrudPort.create(maintenance);
        return new IdValue("maintenanceUuid", maintenanceUuid);
    }

    @Override
    public RetailMaintenanceDto retrieveMaintenance(String maintenanceUuid) {
        return retailMaintenanceCrudPort.retrieve(maintenanceUuid)
                .map(retailMaintenanceDtoMapper::toRetailMaintenanceDto)
                .orElseThrow(() -> new ErpRuntimeException(MAINTENANCE_1000, maintenanceUuid));
    }

    @Override
    public List<RetailMaintenanceDto> retrieveAllMaintenance(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return retailMaintenanceCrudPort.retrieveAllMaintenance(wholesalerUuid)
                .stream()
                .map(retailMaintenanceDtoMapper::toRetailMaintenanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateMaintenance(String maintenanceUuid, NameValuePairs nameValuePairs) {
        return retailMaintenanceCrudPort.retrieve(maintenanceUuid).map(maintenance -> {
            boolean modified = retailMaintenanceCrudPort.update(maintenanceUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new MaintenanceRemovedEvent(maintenance, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(maintenanceUuid));
    }

    @Override
    public BooleanValue deleteMaintenance(String maintenanceUuid, boolean permanent) {
        return retailMaintenanceCrudPort.retrieve(maintenanceUuid).map(maintenance -> {
            boolean removed = retailMaintenanceCrudPort.deleteMaintenance(maintenance, permanent);
            if (removed) {
                // domainEventPublisher.publish(new MaintenanceRemovedEvent(maintenance, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(maintenanceUuid));
    }
}
