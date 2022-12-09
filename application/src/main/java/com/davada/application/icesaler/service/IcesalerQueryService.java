package com.davada.application.icesaler.service;

import com.davada.application.icesaler.persistence.IcesalerPersistenceAdapter;
import com.davada.application.icesaler.service.port.IcesalerQueryUseCase;

import com.davada.domain.icesaler.condition.IcesalerCondition;
import com.davada.domain.icesaler.entity.Icesaler;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class IcesalerQueryService implements IcesalerQueryUseCase {

    private final IcesalerPersistenceAdapter icesalerPersistenceAdapter;

    @Override
    public List<Icesaler> retrieveListByCondition(IcesalerCondition condition, int offset, int limit) {
        return icesalerPersistenceAdapter.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(IcesalerCondition condition) {
        return icesalerPersistenceAdapter.countByCondition(condition);
    }
}
