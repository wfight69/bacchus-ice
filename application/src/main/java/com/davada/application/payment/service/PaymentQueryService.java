package com.davada.application.payment.service;

import com.davada.application.payment.persistence.PaymentPersistenceAdapter;
import com.davada.application.payment.service.port.PaymentQueryUseCase;
import com.davada.domain.payment.condition.PaymentCondition;
import com.davada.dto.payment.PaymentDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class PaymentQueryService implements PaymentQueryUseCase {
    private final PaymentPersistenceAdapter paymentQueryPort;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentDto> retrieveListByCondition(PaymentCondition condition, int offset, int limit) {
        return paymentQueryPort.retrieveListByCondition(condition, offset, limit)
                .stream()
                .map(paymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countByCondition(PaymentCondition condition) {
        return paymentQueryPort.countByCondition(condition);
    }
}
