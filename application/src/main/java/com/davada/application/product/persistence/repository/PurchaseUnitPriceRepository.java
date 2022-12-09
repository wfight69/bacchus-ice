package com.davada.application.product.persistence.repository;

import com.davada.application.product.persistence.data.PurchaseUnitPriceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseUnitPriceRepository extends JpaRepository<PurchaseUnitPriceData, String> {

    List<PurchaseUnitPriceData> findByPurchaseUnitPriceUuidAndAuditLog_Deleted(String purchaseUnitPriceUuid, Boolean deleted);

    List<PurchaseUnitPriceData> findByProductUuidAndAuditLog_Deleted(String productUuid, Boolean deleted);

    void deleteByProductUuid(String productUuid);
}
