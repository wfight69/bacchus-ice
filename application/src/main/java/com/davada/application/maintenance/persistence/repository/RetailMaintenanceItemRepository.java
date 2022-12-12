package com.davada.application.maintenance.persistence.repository;

import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import com.davada.application.maintenance.persistence.data.RetailMaintenanceItemData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailMaintenanceItemRepository extends JpaRepository<RetailMaintenanceItemData, String> {
    long deleteByMaintenance(RetailMaintenanceData retailMaintenanceData);
}
