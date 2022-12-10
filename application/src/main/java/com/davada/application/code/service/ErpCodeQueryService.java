package com.davada.application.code.service;

import com.davada.application.code.persistence.ErpCodePersistenceAdapter;
import com.davada.application.code.service.port.ErpCodeQueryUseCase;
import com.davada.domain.code.condition.ErpCodeCondition;
import com.davada.domain.code.entity.ErpCode;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodeQueryService implements ErpCodeQueryUseCase {
    private final ErpCodePersistenceAdapter erpCodeQueryPort;

    @Override
    public List<ErpCode> retrieveListByCondition(ErpCodeCondition condition, int offset, int limit) {
        return erpCodeQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(ErpCodeCondition condition) {
        return erpCodeQueryPort.countByCondition(condition);
    }
}
