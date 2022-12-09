package com.davada.application.icesaler.persistence.repository;

import com.davada.application.icesaler.persistence.data.IcesalerData;
import com.davada.domain.icesaler.condition.IcesalerCondition;

import java.util.List;

public interface IcesalerRepositoryCustom {
    List<IcesalerData> findListByCondition(IcesalerCondition condition, int offset, int limit);

    long countByCondition(IcesalerCondition condition);
}
