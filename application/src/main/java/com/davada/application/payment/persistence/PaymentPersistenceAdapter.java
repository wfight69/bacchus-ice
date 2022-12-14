package com.davada.application.payment.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.payment.persistence.data.PaymentData;
import com.davada.application.payment.persistence.repository.PaymentRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;

import com.davada.domain.payment.condition.PaymentCondition;
import com.davada.domain.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class PaymentPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final PaymentDataMapper jpaMapper;
    private final PaymentRepository paymentRepository;

    public void create(final Payment payment) {
        payment.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        var paymentData = jpaMapper.toData(payment);
        paymentData.reCalculateOrder();
        var attachedPaymentData = paymentRepository.save(paymentData);
    }

    public Optional<Payment> retrieve(String orderUuid) {
        return paymentRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(jpaMapper::fromData);
    }

    public List<Payment> retrieveAllPayment(String wholesalerUuid) {
        return paymentRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public boolean update(String orderUuid, NameValuePairs nameValuePairs) {
        return paymentRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(paymentData -> {
                    paymentData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    paymentData.updateValues(nameValuePairs, jpaMapper);
                    if (!nameValuePairs.isEmpty()) {
                        paymentRepository.save(paymentData);
                    }
                    return true;
                }).orElse(false);
    }

    private boolean deletePaymentData(PaymentData paymentData, boolean permanent) {
        if (permanent) {
            paymentRepository.delete(paymentData);
        } else {
            paymentData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
            paymentRepository.save(paymentData);
        }
        return true;
    }

    public boolean deletePayment(Payment payment, boolean permanent) {
        return paymentRepository.findById(payment.getPaymentUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(paymentData -> deletePaymentData(paymentData, permanent))
                .orElse(false);
    }

    public boolean deletePayments(Set<String> paymentUuids, boolean permanent) {
        paymentUuids.forEach(paymentUuid ->
                paymentRepository.findById(paymentUuid)
                        .filter(entity -> !entity.getAuditLog().getDeleted())
                        .map(paymentData -> deletePaymentData(paymentData, permanent)));
        return true;
    }

    public List<Payment> retrieveListByCondition(PaymentCondition condition, int offset, int limit) {
        return paymentRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public long countByCondition(PaymentCondition condition) {
        return paymentRepository.countByCondition(condition);
    }

}
