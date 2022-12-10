package com.davada.application.code.persistence.repository;

import com.davada.application.code.persistence.data.ErpCodeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErpCodeRepository extends JpaRepository<ErpCodeData, String>, ErpCodeRepositoryCustom {

    List<ErpCodeData> findByCodeUuidAndAuditLog_Deleted(String codeUuid, Boolean deleted);

    List<ErpCodeData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean aFalse);
}
