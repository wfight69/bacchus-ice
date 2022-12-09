package com.davada.application.employee.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.employee.persistence.data.EmployeeData;
import com.davada.application.employee.persistence.data.ErpUserData;
import com.davada.application.employee.persistence.repository.EmployeeRepository;
import com.davada.application.employee.persistence.repository.ErpUserRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.util.JsonHelper;
import com.davada.domain.wholesaler.condition.EmployeeCondition;
import com.davada.domain.wholesaler.entity.Employee;
import com.davada.domain.wholesaler.entity.ErpUser;
import com.davada.domain.wholesaler.error.EmployeeErrorCodes;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class EmployeePersistenceAdapter {

    private final EmployeeRepository employeeRepository;
    private final ErpUserRepository erpUserRepository;
    private final ErpRequestContext erpRequestContext;
    private final EmployeeDataMapper jpaMapper;

    private void validateEmployeeCode(String wholesalerUuid, String employeeCode) {
        EmployeeCondition condition = new EmployeeCondition(wholesalerUuid, null, employeeCode, null);
        var employees = retrieveListByCondition(condition, 0, 1);
        if (!employees.isEmpty()) {
            throw new ErpRuntimeException(EmployeeErrorCodes.EMPLOYEE_1002, employeeCode);
        }
    }

    private void validateErpUserLoginId(ErpUser erpUser) {
        if (erpUser != null) {
            var erpUserData = erpUserRepository.findByErpUserLoginId(erpUser.getErpUserLoginId());
            if (erpUserData.isPresent()) {
                throw new ErpRuntimeException(EmployeeErrorCodes.EMPLOYEE_1001, erpUser.getErpUserLoginId());
            }
        }
    }


    public void create(Employee employee) {
        employee.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        EmployeeData employeeData = jpaMapper.toEmployeeData(employee);
        if (employee.getErpUser() != null) {
            validateEmployeeCode(employeeData.getWholesalerUuid(), employee.getEmployeeCode());
            validateErpUserLoginId(employee.getErpUser());
            ErpUserData erpUserData = erpUserRepository.save(jpaMapper.toErpUserData(employee.getErpUser()));
            employeeData.setErpUser(erpUserData);
        }
        employeeRepository.save(employeeData);
    }

    public Optional<Employee> retrieve(String employeeUuid) {
        return employeeRepository.findById(employeeUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted()).map(jpaMapper::fromEmployeeData);
    }

    public List<Employee> retrieveAllEmployee(String wholesalerUuid) {
        return employeeRepository.findAllByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromEmployeeData).collect(Collectors.toList());
    }

    public boolean update(String employeeUuid, NameValuePairs nameValuePairs) {
        return employeeRepository.findById(employeeUuid)
                .map(employeeData -> {
                    nameValuePairs.peek("erpUser",
                            value -> this.validateErpUserLoginId(JsonHelper.fromJson(value, ErpUser.class)));
                    employeeData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = employeeData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        employeeRepository.save(employeeData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(Employee employee, boolean permanent) {
        return employeeRepository.findById(employee.getEmployeeUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(wholesalerData -> {
                    if (permanent) {
                        employeeRepository.delete(wholesalerData);
                    } else {
                        wholesalerData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        employeeRepository.save(wholesalerData);
                    }
                    return true;
                }).orElse(false);
    }


    public List<Employee> retrieveListByCondition(EmployeeCondition condition, int offset, int limit) {
        return employeeRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromEmployeeData)
                .collect(Collectors.toList());
    }

    public long countByCondition(EmployeeCondition condition) {
        return employeeRepository.countByCondition(condition);
    }

}
