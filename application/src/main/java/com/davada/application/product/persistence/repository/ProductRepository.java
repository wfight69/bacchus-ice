package com.davada.application.product.persistence.repository;

import com.davada.application.product.persistence.data.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductData, String>, ProductRepositoryCustom {

    List<ProductData> findByProductUuidAndAuditLog_Deleted(String productUuid, Boolean deleted);

    List<ProductData> findAllByProductCodeAndAuditLog_Deleted(String productCode, Boolean deleted);

    List<ProductData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);
}
