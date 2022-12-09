package com.davada.application.supplier.persistence.repository;

import com.davada.application.supplier.persistence.data.SupplierData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierData, String>, SupplierRepositoryCustom {

    List<SupplierData> findBySupplierUuidAndAuditLog_Deleted(String supplierUuid, Boolean deleted);

    List<SupplierData> findAllBySupplierCodeAndAuditLog_Deleted(String supplierCode, Boolean deleted);

    List<SupplierData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);
}
