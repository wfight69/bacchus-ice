package com.davada.application.purchase.persistence.repository;

import com.davada.application.purchase.persistence.data.PurchaseOrderData;
import com.davada.domain.purchase.condition.PurchaseOrderCondition;

import java.util.List;

public interface PurchaseOrderRepositoryCustom {
    List<PurchaseOrderData> findListByCondition(PurchaseOrderCondition condition, int offset, int limit);

    long countByCondition(PurchaseOrderCondition condition);
}
