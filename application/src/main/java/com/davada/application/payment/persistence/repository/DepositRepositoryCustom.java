package com.davada.application.payment.persistence.repository;

import com.davada.application.payment.persistence.data.DepositData;
import com.davada.domain.payment.condition.DepositCondition;

import java.util.List;

public interface DepositRepositoryCustom {
    List<DepositData> findListByCondition(DepositCondition condition, int offset, int limit);

    long countByCondition(DepositCondition condition);
}
