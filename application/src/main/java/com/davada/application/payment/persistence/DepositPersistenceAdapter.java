package com.davada.application.payment.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.payment.persistence.data.DepositData;
import com.davada.application.payment.persistence.repository.DepositRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;

import com.davada.domain.payment.condition.DepositCondition;
import com.davada.domain.payment.entity.Deposit;
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
public class DepositPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final DepositDataMapper jpaMapper;
    private final DepositRepository depositRepository;

    public void create(final Deposit deposit) {
        deposit.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        var depositData = jpaMapper.toData(deposit);
        depositData.reCalculateOrder();
        var attachedDepositData = depositRepository.save(depositData);
    }

    public Optional<Deposit> retrieve(String orderUuid) {
        return depositRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(jpaMapper::fromData);
    }

    public List<Deposit> retrieveAllDeposit(String wholesalerUuid) {
        return depositRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public boolean update(String orderUuid, NameValuePairs nameValuePairs) {
        return depositRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(depositData -> {
                    depositData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    depositData.updateValues(nameValuePairs, jpaMapper);
                    if (!nameValuePairs.isEmpty()) {
                        depositRepository.save(depositData);
                    }
                    return true;
                }).orElse(false);
    }

    private boolean deleteDepositData(DepositData depositData, boolean permanent) {
        if (permanent) {
            depositRepository.delete(depositData);
        } else {
            depositData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
            depositRepository.save(depositData);
        }
        return true;
    }

    public boolean deleteDeposit(Deposit deposit, boolean permanent) {
        return depositRepository.findById(deposit.getDepositUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(depositData -> deleteDepositData(depositData, permanent))
                .orElse(false);
    }

    public boolean deleteDeposits(Set<String> depositUuids, boolean permanent) {
        depositUuids.forEach(depositUuid ->
                depositRepository.findById(depositUuid)
                        .filter(entity -> !entity.getAuditLog().getDeleted())
                        .map(depositData -> deleteDepositData(depositData, permanent)));
        return true;
    }

    public List<Deposit> retrieveListByCondition(DepositCondition condition, int offset, int limit) {
        return depositRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public long countByCondition(DepositCondition condition) {
        return depositRepository.countByCondition(condition);
    }

}
