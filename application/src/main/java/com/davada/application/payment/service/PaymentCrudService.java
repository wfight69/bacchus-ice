package com.davada.application.payment.service;

import com.davada.application.payment.persistence.PaymentPersistenceAdapter;
import com.davada.application.payment.service.port.PaymentCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;

import com.davada.domain.payment.entity.Payment;
import com.davada.dto.payment.PaymentDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.payment.error.PurchasePaymentErrorCodes.PURCHASE_PAYMENT_1000;


@ApplicationScoped
@RequiredArgsConstructor
public class PaymentCrudService implements PaymentCrudUseCase {

    public final PaymentPersistenceAdapter paymentCrudPort;
    private final PaymentMapper paymentMapper;

    @Override
    public IdValue createPayment(CreatePaymentCommand command) {
        String paymentUuid = ErpId.newId().getUuid().toString();
        Payment payment = paymentMapper.toDomain(paymentUuid, command);
        payment.refineValues();
        paymentCrudPort.create(payment);
        return new IdValue("paymentUuid", paymentUuid);
    }

    @Override
    public PaymentDto retrievePayment(String paymentUuid) {
        return paymentCrudPort.retrieve(paymentUuid)
                .map(paymentMapper::toPaymentDto)
                .orElseThrow(() -> new ErpRuntimeException(PURCHASE_PAYMENT_1000, paymentUuid));
    }

    @Override
    public List<PaymentDto> retrieveAllPayment(String wholesalerUuid) {
        return paymentCrudPort.retrieveAllPayment(wholesalerUuid)
                .stream().map(paymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updatePayment(String paymentUuid, NameValuePairs nameValuePairs) {
        return paymentCrudPort.retrieve(paymentUuid).map(order -> {
            boolean modified = paymentCrudPort.update(paymentUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(paymentUuid));
    }

    @Override
    public BooleanValue deletePayment(String paymentUuid, boolean permanent) {
        return paymentCrudPort.retrieve(paymentUuid).map(order -> {
            boolean removed = paymentCrudPort.deletePayment(order, permanent);
            if (removed) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(paymentUuid));
    }

    @Override
    public BooleanValue deletePayments(DeletePaymentCommand command) {
        boolean removed = paymentCrudPort.deletePayments(command.getPaymentUuids(), command.isPermanent());
        if (removed) {
            // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
        }
        return new BooleanValue("removed", removed);
    }
}
