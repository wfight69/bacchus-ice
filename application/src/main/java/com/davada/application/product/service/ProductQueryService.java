package com.davada.application.product.service;

import com.davada.application.product.persistence.ProductPersistenceAdapter;
import com.davada.application.product.service.port.ProductQueryUseCase;
import com.davada.domain.product.condition.ProductCondition;
import com.davada.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductQueryService implements ProductQueryUseCase {
    private final ProductPersistenceAdapter productQueryPort;

    @Override
    public List<Product> retrieveListByCondition(ProductCondition condition, int offset, int limit) {
        return productQueryPort.retrieveListByCondition(condition, offset, limit);
    }

    @Override
    public long countByCondition(ProductCondition condition) {
        return productQueryPort.countByCondition(condition);
    }
}
