package com.davada.application.icesaler.persistence.repository;


import com.davada.application.icesaler.persistence.data.IcesalerData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IcesalerRepository extends JpaRepository<IcesalerData, String>, IcesalerRepositoryCustom {
    List<IcesalerData> findByIcesalerCodeAndAuditLog_Deleted(String wholesalerCode, Boolean deleted);

    List<IcesalerData> findAllByAuditLog_Deleted(Boolean deleted);
}
