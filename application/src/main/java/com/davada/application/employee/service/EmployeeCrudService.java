package com.davada.application.employee.service;

import com.davada.application.employee.persistence.EmployeePersistenceAdapter;
import com.davada.application.employee.persistence.ErpUserPersistenceAdapter;
import com.davada.application.employee.service.port.EmployeeCrudUseCase;
import com.davada.application.wholesaler.persistence.WholesalerPersistenceAdapter;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.wholesaler.entity.Employee;
import com.davada.domain.wholesaler.entity.ErpUser;
import com.davada.domain.wholesaler.entity.Wholesaler;
import com.davada.domain.wholesaler.error.EmployeeErrorCodes;
import com.davada.domain.wholesaler.error.WholesalerErrorCodes;
import com.davada.dto.wholesaler.EmployeeDto;
import com.davada.dto.wholesaler.EmployeeSimpleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class EmployeeCrudService implements EmployeeCrudUseCase {
    //
    private final WholesalerPersistenceAdapter wholesalerPersistenceAdapter;
    public final EmployeePersistenceAdapter employeeCrudPort;
    public final ErpUserPersistenceAdapter erpUserCrudPort;
    private final EmployeeDomainMapper employeeDomainMapper;
    private final EmployeeDtoMapper employeeDtoMapper;

    @Override
    public IdValue createEmployee(Employee employee) {
        String employeeUuid = ErpId.newId().getUuid().toString();
        String erpUserUuid = ErpId.newId().getUuid().toString();

//        Employee employee = employeeDomainMapper.toDomain(employeeUuid, command);
//        employee.refineValues();
//        if (employee.getErpUser() != null) {
//            employee.getErpUser().setErpUserUuid(erpUserUuid);
//        }
        employee.setEmployeeUuid(employeeUuid);
        employeeCrudPort.create(employee);
        return new IdValue("employeeUuid", employeeUuid);
    }

    @Override
    public Employee retrieveEmployee(String employeeUuid) {
        return employeeCrudPort.retrieve(employeeUuid)
                .orElseThrow(() -> new ErpRuntimeException(EmployeeErrorCodes.EMPLOYEE_1000, employeeUuid));
    }

    @Override
    public EmployeeDto retrieveEmployeeDto(String employeeUuid) {
        Employee employee = employeeCrudPort.retrieve(employeeUuid)
                .orElseThrow(() -> new ErpRuntimeException(EmployeeErrorCodes.EMPLOYEE_1000, employeeUuid));

        Wholesaler wholesaler = wholesalerPersistenceAdapter.retrieve(employee.getWholesalerUuid())
                .orElseThrow(() -> new ErpRuntimeException(WholesalerErrorCodes.WHOLESALER_1000, employee.getWholesalerUuid()));

        return employeeDtoMapper.toEmployeeDto(employee, wholesaler.getContact());
    }

    @Override
    public EmployeeSimpleDto retrieveEmployeeSimpleDto(String employeeUuid) {
        Employee employee = employeeCrudPort.retrieve(employeeUuid)
                .orElseThrow(() -> new ErpRuntimeException(EmployeeErrorCodes.EMPLOYEE_1000, employeeUuid));

        Wholesaler wholesaler = wholesalerPersistenceAdapter.retrieve(employee.getWholesalerUuid())
                .orElseThrow(() -> new ErpRuntimeException(WholesalerErrorCodes.WHOLESALER_1000, employee.getWholesalerUuid()));

        return employeeDtoMapper.toEmployeeSimpleDto(employee, wholesaler.getContact());
    }

    @Override
    public List<Employee> retrieveAllEmployee(String wholesalerUuid) {
        return employeeCrudPort.retrieveAllEmployee(wholesalerUuid);
    }

    @Override
    public BooleanValue updateEmployee(String employeeUuid, NameValuePairs nameValuePairs) {
        return employeeCrudPort.retrieve(employeeUuid).map(employee -> {
            boolean modified = employeeCrudPort.update(employeeUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new EmployeeRemovedEvent(employee, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(employeeUuid));
    }

    @Override
    public BooleanValue deleteEmployee(String employeeUuid, boolean permanent) {
        return employeeCrudPort.retrieve(employeeUuid).map(employee -> {
            boolean removed = employeeCrudPort.delete(employee, permanent);
            if (removed) {
                // domainEventPublisher.publish(new EmployeeRemovedEvent(employee, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(employeeUuid));
    }

    @Override
    public IdValue createErpUser(String employeeUuid, ErpUser erpUser) {
        String erpUserUuid = ErpId.newId().getUuid().toString();
//        ErpUser erpUser = employeeDomainMapper.toDomain(erpUserUuid, command);
//        erpUser.refineValues();
        erpUserCrudPort.create(employeeUuid, erpUser);
        return new IdValue("erpUserUuid", erpUserUuid);
    }

    @Override
    public BooleanValue updateErpUser(String employeeUuid, String erpUserUuid, NameValuePairs nameValuePairs) {
        return erpUserCrudPort.retrieve(erpUserUuid).map(erpUser -> {
            boolean modified = erpUserCrudPort.update(employeeUuid, erpUserUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new ErpUserRemovedEvent(erpUser, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(erpUserUuid));
    }

    @Override
    public BooleanValue deleteErpUser(String employeeUuid, String erpUserUuid, boolean permanent) {
        return erpUserCrudPort.retrieve(erpUserUuid).map(erpUser -> {
            boolean removed = erpUserCrudPort.delete(employeeUuid, erpUserUuid, permanent);
            if (removed) {
                // domainEventPublisher.publish(new ErpUserRemovedEvent(erpUser, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(erpUserUuid));
    }
}
