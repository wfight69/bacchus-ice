package com.davada.application.product.service.port;

import com.davada.domain.product.condition.ProductCondition;
import com.davada.domain.product.entity.Product;

import java.util.List;

public interface ProductQueryUseCase {
    List<Product> retrieveListByCondition(ProductCondition condition, int offset, int limit);

    long countByCondition(ProductCondition condition);
}
