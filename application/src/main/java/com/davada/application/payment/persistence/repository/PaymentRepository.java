package com.davada.application.payment.persistence.repository;

import com.davada.application.payment.persistence.data.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentData, String>, PaymentRepositoryCustom {
    List<PaymentData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);
}
