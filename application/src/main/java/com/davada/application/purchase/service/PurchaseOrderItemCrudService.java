package com.davada.application.purchase.service;

import com.davada.application.purchase.persistence.PurchaseOrderItemPersistenceAdapter;
import com.davada.application.purchase.service.port.PurchaseOrderItemCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.purchase.entity.PurchaseOrderItem;
import com.davada.dto.purchase.PurchaseOrderItemDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.purchase.error.PurchaseOrderItemErrorCodes.PURCHASE_ORDER_ITEM_1000;

@ApplicationScoped
@RequiredArgsConstructor
public class PurchaseOrderItemCrudService implements PurchaseOrderItemCrudUseCase {

    private final PurchaseOrderItemPersistenceAdapter purchaseOrderItemCrudPort;
    private final PurchaseOrderItemMapper purchaseOrderItemMapper;

    @Override
    public IdValue createPurchaseOrderItem(CreatePurchaseOrderItemCommand command) {
        String purchaseOrderItemUuid = ErpId.newId().toString();
        PurchaseOrderItem purchaseOrderItem = purchaseOrderItemMapper.toDomain(purchaseOrderItemUuid, command);
        purchaseOrderItem.refineValues();
        purchaseOrderItemCrudPort.create(purchaseOrderItem);
        return new IdValue("purchaseOrderItemUuid", purchaseOrderItemUuid);
    }

    @Override
    public PurchaseOrderItemDto retrievePurchaseOrderItem(String purchaseOrderItemUuid) {
        return purchaseOrderItemCrudPort.retrieve(purchaseOrderItemUuid)
                .map(purchaseOrderItemMapper::toDto)
                .orElseThrow(() -> new ErpRuntimeException(PURCHASE_ORDER_ITEM_1000, purchaseOrderItemUuid));
    }

    @Override
    public List<PurchaseOrderItemDto> retrieveAllPurchaseOrderItem(String orderUuid) {
        if (isEmpty(orderUuid)) {
            throw new ErpEntityNotFoundException(orderUuid);
        }
        return purchaseOrderItemCrudPort.retrieveAllPurchaseOrderItem(orderUuid)
                .stream()
                .map(purchaseOrderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updatePurchaseOrderItem(String purchaseOrderItemUuid, NameValuePairs nameValuePairs) {
        return purchaseOrderItemCrudPort.retrieve(purchaseOrderItemUuid).map(purchaseOrderItem -> {
            boolean modified = purchaseOrderItemCrudPort.update(purchaseOrderItemUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new OrderItemRemovedEvent(purchaseOrderItem, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(purchaseOrderItemUuid));
    }

    @Override
    public BooleanValue deletePurchaseOrderItem(String purchaseOrderItemUuid, boolean permanent) {
        return purchaseOrderItemCrudPort.retrieve(purchaseOrderItemUuid).map(purchaseOrderItem -> {
            boolean removed = purchaseOrderItemCrudPort.delete(purchaseOrderItem, permanent);
            if (removed) {
                // domainEventPublisher.publish(new OrderItemRemovedEvent(purchaseOrderItem, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(purchaseOrderItemUuid));
    }
}
