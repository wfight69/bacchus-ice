package com.davada.application.purchase.persistence.repository;

import com.davada.application.purchase.persistence.data.PurchaseOrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderData, String>, PurchaseOrderRepositoryCustom {
    List<PurchaseOrderData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);
}
