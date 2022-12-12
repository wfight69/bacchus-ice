package com.davada.application.maintenance.service;

import com.davada.application.maintenance.persistence.RetailMaintenancePersistenceAdapter;
import com.davada.application.maintenance.service.port.RetailMaintenanceQueryUseCase;
import com.davada.domain.maintenance.condition.RetailMaintenanceCondition;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RetailMaintenanceQueryService implements RetailMaintenanceQueryUseCase {
    private final RetailMaintenancePersistenceAdapter retailMaintenanceQueryPort;

    @Override
    public List<RetailMaintenance> retrieveListByCondition(RetailMaintenanceCondition condition, int offset, int limit) {
        return retailMaintenanceQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(RetailMaintenanceCondition condition) {
        return retailMaintenanceQueryPort.countByCondition(condition);
    }
}
