package com.davada.application.retailshop.service.port;

import com.davada.domain.retailshop.condition.RetailShopCondition;
import com.davada.dto.retailshop.RetailShopDto;

import java.util.List;

public interface RetailShopQueryUseCase {
    List<RetailShopDto> retrieveListByCondition(RetailShopCondition condition, int offset, int limit);

    long countByCondition(RetailShopCondition condition);
}
