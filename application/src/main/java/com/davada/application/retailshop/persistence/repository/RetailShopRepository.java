package com.davada.application.retailshop.persistence.repository;

import com.davada.application.retailshop.persistence.data.RetailShopData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetailShopRepository extends JpaRepository<RetailShopData, String>, RetailShopRepositoryCustom {
    List<RetailShopData> findByRetailShopCodeAndAuditLog_Deleted(String retailShopCode, Boolean aFalse);

    List<RetailShopData> findAllByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean aFalse);
}
