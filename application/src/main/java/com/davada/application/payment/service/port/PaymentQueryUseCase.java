package com.davada.application.payment.service.port;


import com.davada.domain.payment.condition.PaymentCondition;
import com.davada.dto.payment.PaymentDto;

import java.util.List;

public interface PaymentQueryUseCase {
    List<PaymentDto> retrieveListByCondition(PaymentCondition condition, int offset, int limit);

    long countByCondition(PaymentCondition condition);
}
