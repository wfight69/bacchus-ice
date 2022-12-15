package com.davada.application.purchase.persistence;

import com.davada.application.purchase.persistence.repository.PurchaseOrderItemRepository;
import com.davada.domain.common.NameValuePairs;

import com.davada.domain.purchase.entity.PurchaseOrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class PurchaseOrderItemPersistenceAdapter {

    private final PurchaseOrderItemDataMapper jpaMapper;
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;

    public void create(final PurchaseOrderItem purchaseOrderItem) {
        purchaseOrderItemRepository.save(jpaMapper.toData(purchaseOrderItem));
    }

    public Optional<PurchaseOrderItem> retrieve(String purchaseOrderItemUuid) {
        return purchaseOrderItemRepository.findById(purchaseOrderItemUuid)
                .map(jpaMapper::fromData);
    }

    public List<PurchaseOrderItem> retrieveAllPurchaseOrderItem(String purchaseOrderUuid) {
        return Collections.emptyList();
    }

    public boolean update(String orderItemUuid, NameValuePairs nameValuePairs) {
        return purchaseOrderItemRepository.findById(orderItemUuid)
                .map(purchaseOrderItemData -> {
                    boolean dirty = purchaseOrderItemData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        purchaseOrderItemRepository.save(purchaseOrderItemData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(PurchaseOrderItem purchaseOrderItem, boolean permanent) {
        return purchaseOrderItemRepository.findById(purchaseOrderItem.getPurchaseOrderItemUuid())
                .map(purchaseOrderItemData -> {
                    purchaseOrderItemRepository.delete(purchaseOrderItemData);
                    return true;
                }).orElse(false);
    }
}
