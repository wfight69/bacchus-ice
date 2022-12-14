package com.davada.application.purchase.service;

import com.davada.application.purchase.persistence.PurchaseOrderPersistenceAdapter;
import com.davada.application.purchase.service.port.PurchaseOrderQueryUseCase;
import com.davada.domain.purchase.condition.PurchaseOrderCondition;
import com.davada.dto.purchase.PurchaseOrderDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class PurchaseOrderQueryService implements PurchaseOrderQueryUseCase {
    private final PurchaseOrderPersistenceAdapter purchaseOrderQueryPort;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public List<PurchaseOrderDto> retrieveListByCondition(PurchaseOrderCondition condition, int offset, int limit) {
        return purchaseOrderQueryPort.retrieveListByCondition(condition, offset, limit)
                .stream()
                .map(purchaseOrderMapper::toPurchaseOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countByCondition(PurchaseOrderCondition condition) {
        return purchaseOrderQueryPort.countByCondition(condition);
    }
}
