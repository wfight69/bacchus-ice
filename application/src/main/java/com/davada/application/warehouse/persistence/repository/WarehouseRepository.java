package com.davada.application.warehouse.persistence.repository;

import com.davada.application.warehouse.persistence.data.WarehouseData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<WarehouseData, String>, WarehouseRepositoryCustom {

    List<WarehouseData> findByWarehouseUuidAndAuditLog_Deleted(String warehouseUuid, Boolean deleted);

    List<WarehouseData> findAllByWarehouseCodeAndAuditLog_Deleted(String warehouseCode, Boolean deleted);

    List<WarehouseData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);
}
