package com.davada.application.supplier.persistence.repository;

import com.davada.application.supplier.persistence.data.SupplierData;
import com.davada.domain.supplier.condition.SupplierCondition;

import java.util.List;

public interface SupplierRepositoryCustom {
    List<SupplierData> findListByCondition(SupplierCondition condition, int offset, int limit);

    long countByCondition(SupplierCondition condition);
}
