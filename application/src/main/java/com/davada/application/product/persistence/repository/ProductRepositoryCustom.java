package com.davada.application.product.persistence.repository;


import com.davada.application.product.persistence.data.ProductData;
import com.davada.domain.product.condition.ProductCondition;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductData> findListByCondition(ProductCondition condition, int offset, int limit);

    long countByCondition(ProductCondition condition);
}
