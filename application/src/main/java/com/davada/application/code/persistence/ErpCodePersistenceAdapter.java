package com.davada.application.code.persistence;

import com.davada.application.code.persistence.data.ErpCodeData;
import com.davada.application.code.persistence.repository.ErpCodeRepository;
import com.davada.application.common.ErpRequestContext;
import com.davada.domain.code.condition.ErpCodeCondition;
import com.davada.domain.code.entity.ErpCode;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.ErpId;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodePersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final ErpCodeDataMapper jpaMapper;
    private final ErpCodeRepository erpCodeRepository;

    public void create(final ErpCode erpCode) {
        erpCode.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        erpCodeRepository.save(jpaMapper.toErpCodeData(erpCode));
    }

    public void save(List<ErpCode> erpCodes) {
        erpCodes.forEach(erpCode -> {
            if (StringUtils.isEmpty(erpCode.getCodeUuid())) {
                erpCode.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
                ErpCodeData erpCodeData = jpaMapper.toErpCodeData(ErpId.newId().getUuid().toString(), erpCode);
                erpCodeRepository.save(erpCodeData);
            } else {
                erpCodeRepository.findById(erpCode.getCodeUuid())
                        .ifPresent(erpCodeData -> {
                            erpCodeData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                            erpCodeData.setName(erpCode.getName());
                            erpCodeData.setLabel(erpCode.getLabel());
                            erpCodeData.setRefCode(erpCode.getRefCode());
                            erpCodeData.setDescription(erpCode.getDescription());
                        });
            }
        });
    }

    public Optional<ErpCode> retrieve(String codeUuid) {
        return erpCodeRepository.findById(codeUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(entity -> Optional.of(jpaMapper.fromErpCodeData(entity)))
                .orElse(Optional.empty());
    }

    public List<ErpCode> retrieveAllErpCode(String wholesalerUuid) {
        return erpCodeRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromErpCodeData).collect(Collectors.toList());
    }

    public boolean update(String codeUuid, NameValuePairs nameValuePairs) {
        return erpCodeRepository.findById(codeUuid)
                .map(ErpCodeData -> {
                    ErpCodeData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = ErpCodeData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        erpCodeRepository.save(ErpCodeData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(ErpCode erpCode, boolean permanent) {
        return erpCodeRepository.findById(erpCode.getCodeUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(ErpCodeData -> {
                    if (permanent) {
                        erpCodeRepository.delete(ErpCodeData);
                    } else {
                        ErpCodeData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        erpCodeRepository.save(ErpCodeData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<ErpCode> retrieveListByCondition(ErpCodeCondition condition, int offset, int limit) {
        return erpCodeRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromErpCodeData)
                .collect(Collectors.toList());
    }

    public long countByCondition(ErpCodeCondition condition) {
        return erpCodeRepository.countByCondition(condition);
    }
}
