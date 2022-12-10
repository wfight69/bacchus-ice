package com.davada.application.code.persistence.repository;


import com.davada.application.code.persistence.data.ErpCodeSetData;
import com.davada.domain.code.condition.ErpCodeSetCondition;

import java.util.List;

public interface ErpCodeSetRepositoryCustom {
    List<ErpCodeSetData> findListByCondition(ErpCodeSetCondition condition, int offset, int limit);

    long countByCondition(ErpCodeSetCondition condition);
}
