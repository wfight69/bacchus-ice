package com.davada.application.wholesaler.service;

import com.davada.application.wholesaler.persistence.WholesalerPersistenceAdapter;
import com.davada.application.wholesaler.persistence.repository.WholesalerRepositoryCustomImpl;
import com.davada.application.wholesaler.service.port.WholesalerQueryUseCase;
import com.davada.domain.wholesaler.condition.WholesalerCondition;
import com.davada.domain.wholesaler.entity.Wholesaler;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class WholesalerQueryService implements WholesalerQueryUseCase {

    private final WholesalerPersistenceAdapter wholesalerPersistenceAdapter;

    @Override
    public List<Wholesaler> retrieveListByCondition(WholesalerCondition condition, int offset, int limit) {
        return wholesalerPersistenceAdapter.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(WholesalerCondition condition) {
        return wholesalerPersistenceAdapter.countByCondition(condition);
    }
}
