package com.davada.application.employee.persistence.repository;

import com.davada.application.employee.persistence.data.ErpUserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ErpUserRepository extends JpaRepository<ErpUserData, String> {
    Optional<ErpUserData> findByErpUserLoginId(String erpUserLoginId);
}
