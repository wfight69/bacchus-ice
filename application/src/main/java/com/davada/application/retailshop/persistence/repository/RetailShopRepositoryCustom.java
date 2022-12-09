package com.davada.application.retailshop.persistence.repository;

import com.davada.application.retailshop.persistence.data.RetailShopData;
import com.davada.domain.retailshop.condition.RetailShopCondition;

import java.util.List;

public interface RetailShopRepositoryCustom {
    List<RetailShopData> findListByCondition(RetailShopCondition condition, int offset, int limit);

    long countByCondition(RetailShopCondition condition);

}
