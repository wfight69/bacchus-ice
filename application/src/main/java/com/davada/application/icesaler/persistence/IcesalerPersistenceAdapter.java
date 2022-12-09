package com.davada.application.icesaler.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.icesaler.persistence.repository.IcesalerRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.icesaler.condition.IcesalerCondition;
import com.davada.domain.icesaler.entity.Icesaler;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.icesaler.error.IcesalerErrorCodes.ICESALER_1001;
import static com.davada.domain.icesaler.error.IcesalerErrorCodes.ICESALER_1002;

@ApplicationScoped
@RequiredArgsConstructor
public class IcesalerPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final IcesalerDataMapper jpaMapper;
    private final IcesalerRepository icesalerRepository;

    private void validateIcesalerCode(String icesalerCode) {
        if (isEmpty(icesalerCode)) {
            throw new ErpRuntimeException(ICESALER_1002, icesalerCode);
        }
        var list = icesalerRepository.findByIcesalerCodeAndAuditLog_Deleted(icesalerCode, Boolean.FALSE);
        if (!list.isEmpty()) {
            throw new ErpRuntimeException(ICESALER_1001, icesalerCode);
        }
    }

//    @EnableAuditLog(EnableAuditLog.CommandType.CREATE)
    public void create(final Icesaler icesaler) {
        validateIcesalerCode(icesaler.getIcesalerCode());
        icesaler.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        icesalerRepository.save(jpaMapper.toIcesalerData(icesaler));
    }

    public Optional<Icesaler> retrieve(String icesalerUuid) {
        return icesalerRepository.findById(icesalerUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted()).map(jpaMapper::fromIcesalerData);
    }

    public List<Icesaler> retrieveAllIcesaler() {
        return icesalerRepository.findAllByAuditLog_Deleted(Boolean.FALSE).stream()
                .map(jpaMapper::fromIcesalerData).collect(Collectors.toList());
    }

    public boolean update(String icesalerUuid, NameValuePairs nameValuePairs) {
        return icesalerRepository.findById(icesalerUuid)
                .map(icesalerData -> {
                    nameValuePairs.peek("icesalerCode", this::validateIcesalerCode);
                    icesalerData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = icesalerData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        icesalerRepository.save(icesalerData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(Icesaler icesaler, boolean permanent) {
        return icesalerRepository.findById(icesaler.getIcesalerUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(icesalerData -> {
                    if (permanent) {
                        icesalerRepository.delete(icesalerData);
                    } else {
                        icesalerData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        icesalerRepository.save(icesalerData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<Icesaler> retrieveListByCondition(IcesalerCondition condition, int offset, int limit) {
        return icesalerRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromIcesalerData)
                .collect(Collectors.toList());
    }

    public long countByCondition(IcesalerCondition condition) {
        return icesalerRepository.countByCondition(condition);
    }
}
