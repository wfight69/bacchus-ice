package com.davada.application.payment.persistence.repository;


import com.davada.application.payment.persistence.data.PaymentData;
import com.davada.domain.payment.condition.PaymentCondition;

import java.util.List;

public interface PaymentRepositoryCustom {
    List<PaymentData> findListByCondition(PaymentCondition condition, int offset, int limit);

    long countByCondition(PaymentCondition condition);
}
