package com.davada.application.code.service;

import com.davada.application.code.persistence.ErpCodeSetPersistenceAdapter;
import com.davada.application.code.service.port.ErpCodeSetQueryUseCase;
import com.davada.domain.code.condition.ErpCodeSetCondition;
import com.davada.domain.code.entity.ErpCodeSet;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodeSetQueryService implements ErpCodeSetQueryUseCase {
    private final ErpCodeSetPersistenceAdapter erpCodeSetQueryPort;

    @Override
    public List<ErpCodeSet> retrieveListByCondition(ErpCodeSetCondition condition, int offset, int limit) {
        return erpCodeSetQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(ErpCodeSetCondition condition) {
        return erpCodeSetQueryPort.countByCondition(condition);
    }
}
