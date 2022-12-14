package com.davada.application.payment.service.port;

import com.davada.domain.payment.condition.DepositCondition;
import com.davada.dto.payment.DepositDto;

import java.util.List;

public interface DepositQueryUseCase {
    List<DepositDto> retrieveListByCondition(DepositCondition condition, int offset, int limit);

    long countByCondition(DepositCondition condition);
}
