package com.davada.application.code.service.port;


import com.davada.domain.code.condition.ErpCodeCondition;
import com.davada.domain.code.entity.ErpCode;

import java.util.List;

public interface ErpCodeQueryUseCase {

    List<ErpCode> retrieveListByCondition(ErpCodeCondition condition, int offset, int limit);

    long countByCondition(ErpCodeCondition condition);
}
