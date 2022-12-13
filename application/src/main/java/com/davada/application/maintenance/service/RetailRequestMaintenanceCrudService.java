package com.davada.application.maintenance.service;

import com.davada.application.maintenance.persistence.RetailMaintenancePersistenceAdapter;
import com.davada.application.maintenance.service.port.RetailRequestMaintenanceCrudUseCase;
import com.davada.application.retailshop.persistence.RetailShopPersistenceAdapter;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.maintenance.error.RetailRequestMaintenanceErrorCodes.REQUEST_MAINTENANCE_2001;
import static com.davada.domain.maintenance.error.RetailRequestMaintenanceErrorCodes.REQUEST_MAINTENANCE_2002;

@ApplicationScoped
@RequiredArgsConstructor
public class RetailRequestMaintenanceCrudService implements RetailRequestMaintenanceCrudUseCase {

    private final RetailRequestMaintenanceDomainMapper retailRequestMaintenanceDomainMapper;

    private final RetailMaintenancePersistenceAdapter retailMaintenanceCrudPort;

    // FIXME: external local port 로 변경할것.
    private final RetailShopPersistenceAdapter retailShopCrudUseCase;

    @Override
    public IdValue createRequestMaintenance(RetailRequestMaintenanceCrudUseCase.CreateRequestMaintenanceCommand command) {
        if (isEmpty(command.getIcesalerUuid())) {
            throw new ErpRuntimeException(REQUEST_MAINTENANCE_2001, command.getIcesalerUuid());
        }
        if (isEmpty(command.getWholesalerUuid())) {
            throw new ErpRuntimeException(REQUEST_MAINTENANCE_2002, command.getWholesalerUuid());
        }

        var maintenanceUuid = ErpId.newId().getUuid().toString();
        // 전화번호로 소매점 찾기
        //RetailShopDto retailShopDto = retailShopCrudUseCase.findByRetailMaintenanceTelephone(command.getRetailMaintenanceTelephone()).orElse(null);
        RetailMaintenance retailMaintenance = retailRequestMaintenanceDomainMapper.toDomain(maintenanceUuid, command);
        retailMaintenance.refineValues();
        retailMaintenance.registerRequestMaintenanceStatus();
        retailMaintenanceCrudPort.create(retailMaintenance);
        return new IdValue("requestMaintenanceUuid", maintenanceUuid);
    }

    @Override
    public BooleanValue deleteRequestMaintenance(String requestMaintenanceUuid, boolean permanent) {
        return retailMaintenanceCrudPort.retrieve(requestMaintenanceUuid).map(maintenance -> {
            boolean removed = retailMaintenanceCrudPort.deleteRequestMaintenance(maintenance, permanent);
            if (removed) {
                // domainEventPublisher.publish(new MaintenanceRemovedEvent(maintenance, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(requestMaintenanceUuid));
    }

    @Override
    public BooleanValue deleteRequestMaintenances(DeleteRequestMaintenanceCommand command) {
        boolean removed = retailMaintenanceCrudPort.deleteRequestMaintenances(command.getRequestMaintenanceUuids(), command.isPermanent());
        if (removed) {
            // domainEventPublisher.publish(new MaintenanceRemovedEvent(maintenance, permanent));
        }
        return new BooleanValue("removed", removed);
    }
}
