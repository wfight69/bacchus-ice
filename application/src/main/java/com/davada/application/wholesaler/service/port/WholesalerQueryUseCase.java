package com.davada.application.wholesaler.service.port;

import com.davada.domain.wholesaler.condition.WholesalerCondition;
import com.davada.domain.wholesaler.entity.Wholesaler;

import java.util.List;

public interface WholesalerQueryUseCase {

    List<Wholesaler> retrieveListByCondition(WholesalerCondition condition, int offset, int limit);

    long countByCondition(WholesalerCondition condition);

}
