package com.davada.application.maintenance.service;

import com.davada.application.maintenance.persistence.RetailMaintenancePersistenceAdapter;
import com.davada.application.maintenance.service.port.RetailRequestMaintenanceQueryUseCase;
import com.davada.domain.maintenance.condition.RetailRequestMaintenanceCondition;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RetailRequestMaintenanceQueryService implements RetailRequestMaintenanceQueryUseCase {
    private final RetailMaintenancePersistenceAdapter retailRequestMaintenanceQueryPort;

    @Override
    public List<RetailMaintenance> retrieveListByCondition(RetailRequestMaintenanceCondition condition, int offset, int limit) {
        return retailRequestMaintenanceQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(RetailRequestMaintenanceCondition condition) {
        return retailRequestMaintenanceQueryPort.countByCondition(condition);
    }
}