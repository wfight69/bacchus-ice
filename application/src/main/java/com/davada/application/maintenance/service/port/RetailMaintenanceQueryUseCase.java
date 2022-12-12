package com.davada.application.maintenance.service.port;

import com.davada.domain.maintenance.condition.RetailMaintenanceCondition;
import com.davada.domain.maintenance.entity.RetailMaintenance;

import java.util.List;

public interface RetailMaintenanceQueryUseCase {
    List<RetailMaintenance> retrieveListByCondition(RetailMaintenanceCondition condition, int offset, int limit);

    long countByCondition(RetailMaintenanceCondition condition);
}
