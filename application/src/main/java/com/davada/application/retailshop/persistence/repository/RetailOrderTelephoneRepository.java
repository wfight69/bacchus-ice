package com.davada.application.retailshop.persistence.repository;

import com.davada.application.retailshop.persistence.data.RetailOrderTelephoneData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RetailOrderTelephoneRepository extends JpaRepository<RetailOrderTelephoneData, String> {

    Optional<RetailOrderTelephoneData> findByTelephoneAndAuditLog_Deleted(String telephone, Boolean aFalse);

    List<RetailOrderTelephoneData> findAllByRetailShopUuidAndAuditLog_Deleted(String retailShopUuid, Boolean deleted);
}
