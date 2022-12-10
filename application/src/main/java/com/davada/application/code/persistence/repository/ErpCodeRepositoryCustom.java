package com.davada.application.code.persistence.repository;


import com.davada.application.code.persistence.data.ErpCodeData;
import com.davada.domain.code.condition.ErpCodeCondition;

import java.util.List;

public interface ErpCodeRepositoryCustom {
    List<ErpCodeData> findListByCondition(ErpCodeCondition condition, int offset, int limit);

    long countByCondition(ErpCodeCondition condition);
}
