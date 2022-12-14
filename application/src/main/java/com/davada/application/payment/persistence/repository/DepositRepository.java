package com.davada.application.payment.persistence.repository;

import com.davada.application.payment.persistence.data.DepositData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<DepositData, String>, DepositRepositoryCustom {
    List<DepositData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);
}
