package com.davada.application.maintenance.service.port;


import com.davada.domain.maintenance.condition.RetailRequestMaintenanceCondition;
import com.davada.domain.maintenance.entity.RetailMaintenance;

import java.util.List;

public interface RetailRequestMaintenanceQueryUseCase {
    List<RetailMaintenance> retrieveListByCondition(RetailRequestMaintenanceCondition condition, int offset, int limit);

    long countByCondition(RetailRequestMaintenanceCondition condition);
    
}
