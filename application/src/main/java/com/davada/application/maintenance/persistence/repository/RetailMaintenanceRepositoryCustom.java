package com.davada.application.maintenance.persistence.repository;

import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import com.davada.domain.maintenance.condition.RetailMaintenanceCondition;

import java.util.List;

public interface RetailMaintenanceRepositoryCustom {
    List<RetailMaintenanceData> findListByCondition(RetailMaintenanceCondition condition, int offset, int limit);

    long countByCondition(RetailMaintenanceCondition condition);
}
