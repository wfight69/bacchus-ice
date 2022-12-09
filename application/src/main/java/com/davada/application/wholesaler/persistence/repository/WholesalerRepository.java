package com.davada.application.wholesaler.persistence.repository;


import com.davada.application.wholesaler.persistence.data.WholesalerData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WholesalerRepository extends JpaRepository<WholesalerData, String>, WholesalerRepositoryCustom {
    List<WholesalerData> findByWholesalerCodeAndAuditLog_Deleted(String wholesalerCode, Boolean deleted);

    List<WholesalerData> findAllByAuditLog_Deleted(Boolean deleted);
}
