package com.davada.application.maintenance.persistence.repository;

import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import com.davada.domain.maintenance.condition.RetailRequestMaintenanceCondition;

import java.util.List;

public interface RetailRequestMaintenanceRepositoryCustom {
    List<RetailMaintenanceData> findListByCondition(RetailRequestMaintenanceCondition condition, int offset, int limit);

    long countByCondition(RetailRequestMaintenanceCondition condition);
}
