package com.davada.application.purchase.service;

import com.davada.application.purchase.persistence.PurchaseOrderPersistenceAdapter;
import com.davada.application.purchase.service.port.PurchaseOrderCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;

import com.davada.domain.purchase.entity.PurchaseOrder;
import com.davada.dto.purchase.PurchaseOrderDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.purchase.error.PurchaseOrderErrorCodes.PURCHASE_ORDER_1000;

@ApplicationScoped
@RequiredArgsConstructor
public class PurchaseOrderCrudService implements PurchaseOrderCrudUseCase {

    public final PurchaseOrderPersistenceAdapter purchaseOrderCrudPort;

    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public IdValue createPurchaseOrder(CreatePurchaseOrderCommand command) {
        String purchaseOrderUuid = ErpId.newId().getUuid().toString();
        PurchaseOrder purchaseOrder = purchaseOrderMapper.toDomain(purchaseOrderUuid, command);
        purchaseOrder.refineValues();
        purchaseOrderCrudPort.create(purchaseOrder);
        return new IdValue("purchaseOrderUuid", purchaseOrderUuid);
    }

    @Override
    public PurchaseOrderDto retrievePurchaseOrder(String purchaseOrderUuid) {
        return purchaseOrderCrudPort.retrieve(purchaseOrderUuid)
                .map(purchaseOrderMapper::toPurchaseOrderDto)
                .orElseThrow(() -> new ErpRuntimeException(PURCHASE_ORDER_1000, purchaseOrderUuid));
    }

    @Override
    public List<PurchaseOrderDto> retrieveAllPurchaseOrder(String wholesalerUuid) {
        return purchaseOrderCrudPort.retrieveAllPurchaseOrder(wholesalerUuid)
                .stream().map(purchaseOrderMapper::toPurchaseOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updatePurchaseOrder(String purchaseOrderUuid, NameValuePairs nameValuePairs) {
        return purchaseOrderCrudPort.retrieve(purchaseOrderUuid).map(order -> {
            boolean modified = purchaseOrderCrudPort.update(purchaseOrderUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(purchaseOrderUuid));
    }

    @Override
    public BooleanValue deletePurchaseOrder(String purchaseOrderUuid, boolean permanent) {
        return purchaseOrderCrudPort.retrieve(purchaseOrderUuid).map(order -> {
            boolean removed = purchaseOrderCrudPort.deletePurchaseOrder(order, permanent);
            if (removed) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(purchaseOrderUuid));
    }

    @Override
    public BooleanValue deletePurchaseOrders(DeletePurchaseOrderCommand command) {
        boolean removed = purchaseOrderCrudPort.deletePurchaseOrders(command.getPurchaseOrderUuids(), command.isPermanent());
        if (removed) {
            // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
        }
        return new BooleanValue("removed", removed);
    }
}
