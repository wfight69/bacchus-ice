package com.davada.application.wholesaler.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.wholesaler.persistence.repository.WholesalerRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.wholesaler.condition.WholesalerCondition;
import com.davada.domain.wholesaler.entity.Wholesaler;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.wholesaler.error.WholesalerErrorCodes.WHOLESALER_1001;
import static com.davada.domain.wholesaler.error.WholesalerErrorCodes.WHOLESALER_1002;

@ApplicationScoped
@RequiredArgsConstructor
public class WholesalerPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final WholesalerDataMapper jpaMapper;
    private final WholesalerRepository wholesalerRepository;

    private void validateWholesalerCode(String wholesalerCode) {
        if (isEmpty(wholesalerCode)) {
            throw new ErpRuntimeException(WHOLESALER_1002, wholesalerCode);
        }
        var list = wholesalerRepository.findByWholesalerCodeAndAuditLog_Deleted(wholesalerCode, Boolean.FALSE);
        if (!list.isEmpty()) {
            throw new ErpRuntimeException(WHOLESALER_1001, wholesalerCode);
        }
    }

//    @EnableAuditLog(EnableAuditLog.CommandType.CREATE)
    public void create(final Wholesaler wholesaler) {
        validateWholesalerCode(wholesaler.getWholesalerCode());
        wholesaler.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        wholesalerRepository.save(jpaMapper.toWholesalerData(wholesaler));
    }

    public Optional<Wholesaler> retrieve(String wholesalerUuid) {
        return wholesalerRepository.findById(wholesalerUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted()).map(jpaMapper::fromWholesalerData);
    }

    public List<Wholesaler> retrieveAllWholesaler() {
        return wholesalerRepository.findAllByAuditLog_Deleted(Boolean.FALSE).stream()
                .map(jpaMapper::fromWholesalerData).collect(Collectors.toList());
    }

    public boolean update(String wholesalerUuid, NameValuePairs nameValuePairs) {
        return wholesalerRepository.findById(wholesalerUuid)
                .map(wholesalerData -> {
                    nameValuePairs.peek("wholesalerCode", this::validateWholesalerCode);
                    wholesalerData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = wholesalerData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        wholesalerRepository.save(wholesalerData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(Wholesaler wholesaler, boolean permanent) {
        return wholesalerRepository.findById(wholesaler.getWholesalerUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(wholesalerData -> {
                    if (permanent) {
                        wholesalerRepository.delete(wholesalerData);
                    } else {
                        wholesalerData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        wholesalerRepository.save(wholesalerData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<Wholesaler> retrieveListByCondition(WholesalerCondition condition, int offset, int limit) {
        return wholesalerRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromWholesalerData)
                .collect(Collectors.toList());
    }

    public long countByCondition(WholesalerCondition condition) {
        return wholesalerRepository.countByCondition(condition);
    }
}
