package com.davada.application.purchase.service.port;


import com.davada.domain.purchase.condition.PurchaseOrderCondition;
import com.davada.dto.purchase.PurchaseOrderDto;

import java.util.List;

public interface PurchaseOrderQueryUseCase {
    List<PurchaseOrderDto> retrieveListByCondition(PurchaseOrderCondition condition, int offset, int limit);

    long countByCondition(PurchaseOrderCondition condition);
}
