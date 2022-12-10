package com.davada.application.code.persistence.repository;

import com.davada.application.code.persistence.data.ErpCodeSetData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErpCodeSetRepository extends JpaRepository<ErpCodeSetData, String>, ErpCodeSetRepositoryCustom {

    List<ErpCodeSetData> findByCodeSetUuidAndAuditLog_Deleted(String codeSetUuid, Boolean deleted);

    List<ErpCodeSetData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean aFalse);
}
