package com.davada.application.supplier.service;

import com.davada.application.supplier.persistence.SupplierPersistenceAdapter;
import com.davada.application.supplier.service.port.SupplierQueryUseCase;
import com.davada.domain.supplier.condition.SupplierCondition;
import com.davada.domain.supplier.entity.Supplier;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class SupplierQueryService implements SupplierQueryUseCase {
    private final SupplierPersistenceAdapter supplierQueryPort;

    @Override
    public List<Supplier> retrieveListByCondition(SupplierCondition condition, int offset, int limit) {
        return supplierQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(SupplierCondition condition) {
        return supplierQueryPort.countByCondition(condition);
    }
}
