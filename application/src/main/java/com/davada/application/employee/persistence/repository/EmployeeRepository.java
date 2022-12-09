package com.davada.application.employee.persistence.repository;

import com.davada.application.employee.persistence.data.EmployeeData;
import com.davada.application.employee.persistence.data.ErpUserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeData, String>, EmployeeRepositoryCustom {
    List<EmployeeData> findAllByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);

    Optional<EmployeeData> findByErpUser_ErpUserUuid(String erpUserUuid);

    Optional<EmployeeData> findByErpUser(ErpUserData erpUser);
}
