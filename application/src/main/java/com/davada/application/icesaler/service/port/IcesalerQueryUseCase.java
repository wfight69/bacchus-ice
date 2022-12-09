package com.davada.application.icesaler.service.port;

import com.davada.domain.icesaler.condition.IcesalerCondition;
import com.davada.domain.icesaler.entity.Icesaler;

import java.util.List;

public interface IcesalerQueryUseCase {

    List<Icesaler> retrieveListByCondition(IcesalerCondition condition, int offset, int limit);

    long countByCondition(IcesalerCondition condition);

}
