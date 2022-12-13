package com.davada.application.maintenance.persistence.repository;

import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RetailMaintenanceRepository extends JpaRepository<RetailMaintenanceData, String>,
        RetailMaintenanceRepositoryCustom, RetailRequestMaintenanceRepositoryCustom {
    List<RetailMaintenanceData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);

    @Query("select r from RetailMaintenanceData r " +
            "where r.wholesalerUuid = :wholesalerUuid " +
            "and r.auditLog.deleted = false " +
            "and order_date between :firstDayOfMonth and :lastDayOfMonth")
    List<RetailMaintenanceData> retrieveMonthlyRequestMaintenanceCount(
            @Param("wholesalerUuid") String wholesalerUuid,
            @Param("firstDayOfMonth") String firstDayOfMonth,
            @Param("lastDayOfMonth") String lastDayOfMonth);
}
