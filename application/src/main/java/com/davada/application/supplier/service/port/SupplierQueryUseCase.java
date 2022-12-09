package com.davada.application.supplier.service.port;


import com.davada.domain.supplier.condition.SupplierCondition;
import com.davada.domain.supplier.entity.Supplier;

import java.util.List;

public interface SupplierQueryUseCase {

    List<Supplier> retrieveListByCondition(SupplierCondition condition, int offset, int limit);

    long countByCondition(SupplierCondition condition);
}
