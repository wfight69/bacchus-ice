package com.davada.application.employee.persistence;

import com.davada.application.employee.persistence.repository.EmployeeRepository;
import com.davada.application.employee.persistence.repository.ErpUserRepository;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.wholesaler.entity.ErpUser;
import com.davada.domain.wholesaler.error.EmployeeErrorCodes;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ErpUserPersistenceAdapter {
    private final EmployeeRepository employeeRepository;
    private final ErpUserRepository erpUserRepository;
    private final EmployeeDataMapper jpaMapper;

    private void validateErpUserLoginId(String erpUserLoginId) {
        if (erpUserLoginId != null) {
            var erpUserData = erpUserRepository.findByErpUserLoginId(erpUserLoginId);
            if (erpUserData.isPresent()) {
                throw new ErpRuntimeException(EmployeeErrorCodes.EMPLOYEE_1001, erpUserLoginId);
            }
        }
    }

    public void create(String employeeUuid, ErpUser erpUser) {
        this.validateErpUserLoginId(erpUser.getErpUserLoginId());
        var erpUserData = erpUserRepository.save(jpaMapper.toErpUserData(erpUser));
        employeeRepository.findById(employeeUuid)
                .ifPresent(entity -> entity.setErpUser(erpUserData));
    }

    public Optional<ErpUser> retrieve(String erpUserUuid) {
        return erpUserRepository.findById(erpUserUuid).map(jpaMapper::fromErpUserData);
    }

    public boolean update(String wholesalerUuid, String erpUserUuid, NameValuePairs nameValuePairs) {
        return erpUserRepository.findById(erpUserUuid)
                .map(erpUserData -> {
                    boolean dirty = erpUserData.updateValues(nameValuePairs, jpaMapper, this::validateErpUserLoginId);
                    if (dirty) {
                        erpUserRepository.save(erpUserData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(String wholesalerUuid, String erpUserUuid, boolean permanent) {
        return erpUserRepository.findById(erpUserUuid)
                .map(erpUserData -> {
                    employeeRepository.findByErpUser(erpUserData)
                            .ifPresent(employeeData -> employeeData.setErpUser(null));
                    erpUserRepository.delete(erpUserData);
                    return true;
                }).orElse(false);

    }
}
