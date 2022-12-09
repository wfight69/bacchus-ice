package com.davada.application.employee.service.port;

import com.davada.domain.wholesaler.entity.LoginUserDetail;

public interface GetLoginUserDetailUseCase {
    LoginUserDetail getLoginUserDetail(String employeeUuid);
}
