package com.davada.application.code.service.port;


import com.davada.domain.code.condition.ErpCodeSetCondition;
import com.davada.domain.code.entity.ErpCodeSet;

import java.util.List;

public interface ErpCodeSetQueryUseCase {

    List<ErpCodeSet> retrieveListByCondition(ErpCodeSetCondition condition, int offset, int limit);

    long countByCondition(ErpCodeSetCondition condition);
}
