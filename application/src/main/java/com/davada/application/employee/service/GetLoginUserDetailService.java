package com.davada.application.employee.service;

import com.davada.application.employee.persistence.EmployeePersistenceAdapter;
import com.davada.application.employee.service.port.GetLoginUserDetailUseCase;
import com.davada.application.wholesaler.persistence.WholesalerPersistenceAdapter;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.wholesaler.entity.LoginUserDetail;
import com.davada.domain.wholesaler.entity.Wholesaler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

import static com.davada.domain.wholesaler.error.EmployeeErrorCodes.EMPLOYEE_1000;
import static com.davada.domain.wholesaler.error.WholesalerErrorCodes.WHOLESALER_1000;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class GetLoginUserDetailService implements GetLoginUserDetailUseCase {
    private final WholesalerPersistenceAdapter wholesalerCrudPort;
    public final EmployeePersistenceAdapter employeeCrudPort;

    @Override
    public LoginUserDetail getLoginUserDetail(String employeeUuid) {
        return employeeCrudPort.retrieve(employeeUuid)
                .map(employee -> {
                    Wholesaler wholesaler = wholesalerCrudPort.retrieve(employee.getWholesalerUuid())
                            .orElseThrow(() -> new ErpRuntimeException(WHOLESALER_1000, employee.getWholesalerUuid()));

                    return new LoginUserDetail(wholesaler.getWholesalerUuid(),
                            wholesaler.getWholesalerCode(),
                            wholesaler.getWholesalerName(),
                            wholesaler.getBusinessNumber(),
                            employee.getEmployeeUuid(),
                            employee.getEmployeeCode(),
                            employee.getEmployeeName(),
                            employee.getErpUser().getSystemAdmin(),
                            wholesaler.getMobile().getSmsSendYn(),
                            wholesaler.getMobile().getFilterUseYn(),
                            wholesaler.getMobile().getFilterNormalMsg(),
                            wholesaler.getMobile().getFilterExceptionMsg(),
                            wholesaler.getVan(),
                            employee.getVanTerm());
                })
                .orElseThrow(() -> new ErpRuntimeException(EMPLOYEE_1000, employeeUuid));
    }
}
