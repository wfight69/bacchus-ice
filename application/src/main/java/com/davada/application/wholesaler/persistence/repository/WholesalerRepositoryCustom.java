package com.davada.application.wholesaler.persistence.repository;

import com.davada.application.wholesaler.persistence.data.WholesalerData;
import com.davada.domain.wholesaler.condition.WholesalerCondition;

import java.util.List;

public interface WholesalerRepositoryCustom {
    List<WholesalerData> findListByCondition(WholesalerCondition condition, int offset, int limit);

    long countByCondition(WholesalerCondition condition);
}
