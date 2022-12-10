package com.davada.application.code.persistence;

import com.davada.application.code.persistence.repository.ErpCodeSetRepository;
import com.davada.application.common.ErpRequestContext;
import com.davada.domain.code.condition.ErpCodeSetCondition;
import com.davada.domain.code.entity.ErpCodeSet;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodeSetPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final ErpCodeSetDataMapper jpaMapper;
    private final ErpCodeSetRepository erpCodeSetRepository;

    public void create(final ErpCodeSet erpCodeSet) {
        erpCodeSet.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        erpCodeSetRepository.save(jpaMapper.toErpCodeSetData(erpCodeSet));
    }

    public Optional<ErpCodeSet> retrieve(String codeSetUuid) {
        return erpCodeSetRepository.findById(codeSetUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(entity -> Optional.of(jpaMapper.fromErpCodeSetData(entity)))
                .orElse(Optional.empty());
    }

    public List<ErpCodeSet> retrieveAllErpCodeSet(String wholesalerUuid) {
        return erpCodeSetRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromErpCodeSetData).collect(Collectors.toList());
    }

    public boolean update(String codeSetUuid, NameValuePairs nameValuePairs) {
        return erpCodeSetRepository.findById(codeSetUuid)
                .map(ErpCodeSetData -> {
                    ErpCodeSetData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = ErpCodeSetData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        erpCodeSetRepository.save(ErpCodeSetData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(ErpCodeSet erpCodeSet, boolean permanent) {
        return erpCodeSetRepository.findById(erpCodeSet.getCodeSetUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(ErpCodeSetData -> {
                    if (permanent) {
                        erpCodeSetRepository.delete(ErpCodeSetData);
                    } else {
                        ErpCodeSetData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        erpCodeSetRepository.save(ErpCodeSetData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<ErpCodeSet> retrieveListByCondition(ErpCodeSetCondition condition, int offset, int limit) {
        return erpCodeSetRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromErpCodeSetData)
                .collect(Collectors.toList());
    }

    public long countByCondition(ErpCodeSetCondition condition) {
        return erpCodeSetRepository.countByCondition(condition);
    }
}
