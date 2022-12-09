package com.davada.application.product.persistence.repository;

import com.davada.application.product.persistence.data.SellingUnitPriceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellingUnitPriceRepository extends JpaRepository<SellingUnitPriceData, String> {

    List<SellingUnitPriceData> findBySellingUnitPriceUuidAndAuditLog_Deleted(String sellingUnitPriceUuid, Boolean deleted);

    List<SellingUnitPriceData> findByProductUuidAndAuditLog_Deleted(String productUuid, Boolean deleted);

    void deleteByProductUuid(String productUuid);
}
