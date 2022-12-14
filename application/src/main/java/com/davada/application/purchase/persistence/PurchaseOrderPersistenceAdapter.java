package com.davada.application.purchase.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.purchase.persistence.data.PurchaseOrderData;
import com.davada.application.purchase.persistence.data.PurchaseOrderItemData;
import com.davada.application.purchase.persistence.repository.PurchaseOrderItemRepository;
import com.davada.application.purchase.persistence.repository.PurchaseOrderRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.ErpId;

import com.davada.domain.purchase.condition.PurchaseOrderCondition;
import com.davada.domain.purchase.entity.PurchaseOrder;
import com.davada.domain.purchase.entity.PurchaseOrderItem;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.JsonHelper.fromJson;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class PurchaseOrderPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final PurchaseOrderDataMapper jpaMapper;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;

    public void create(final PurchaseOrder order) {
        order.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        var purchaseOrderData = jpaMapper.toData(order);
        purchaseOrderData.reCalculateOrder();
        var attachedPurchaseOrderData = purchaseOrderRepository.save(purchaseOrderData);
        for (PurchaseOrderItemData purchaseOrderItemData : purchaseOrderData.getPurchaseOrderItems()) {
            purchaseOrderItemData.setPurchaseOrderItemUuid(ErpId.newId().getUuid().toString());
            purchaseOrderItemData.setPurchaseOrder(attachedPurchaseOrderData);
            purchaseOrderItemRepository.save(purchaseOrderItemData);
        }
    }

    public Optional<PurchaseOrder> retrieve(String orderUuid) {
        return purchaseOrderRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(jpaMapper::fromData);
    }

    public List<PurchaseOrder> retrieveAllPurchaseOrder(String wholesalerUuid) {
        return purchaseOrderRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public boolean update(String orderUuid, NameValuePairs nameValuePairs) {
        return purchaseOrderRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(purchaseOrderData -> {
                    purchaseOrderData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    nameValuePairs.pullOut("purchaseOrderItems",
                            value -> {
                                purchaseOrderItemRepository.deleteByPurchaseOrder(purchaseOrderData);
                                purchaseOrderData.getPurchaseOrderItems().clear();
                                List<PurchaseOrderItem> items = fromJson(value, new TypeToken<List<PurchaseOrderItem>>() {
                                }.getType());
                                if (items != null) {
                                    for (PurchaseOrderItem item : items) {
                                        PurchaseOrderItemData purchaseOrderItemData = jpaMapper.toData(item);
                                        purchaseOrderItemData.setPurchaseOrderItemUuid(ErpId.newId().getUuid().toString());
                                        purchaseOrderItemData.setPurchaseOrder(purchaseOrderData);
                                        PurchaseOrderItemData save = purchaseOrderItemRepository.save(purchaseOrderItemData);
                                        purchaseOrderData.getPurchaseOrderItems().add(save);
                                    }
                                }
                                purchaseOrderData.reCalculateOrder(); // 재계산
                            });
                    purchaseOrderData.updateValues(nameValuePairs, jpaMapper);
                    if (!nameValuePairs.isEmpty()) {
                        purchaseOrderRepository.save(purchaseOrderData);
                    }
                    return true;
                }).orElse(false);
    }

    private boolean deletePurchaseOrderData(PurchaseOrderData purchaseOrderData, boolean permanent) {
        if (permanent) {
            purchaseOrderRepository.delete(purchaseOrderData);
        } else {
            purchaseOrderData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
            purchaseOrderRepository.save(purchaseOrderData);
        }
        return true;
    }

    public boolean deletePurchaseOrder(PurchaseOrder purchaseOrder, boolean permanent) {
        return purchaseOrderRepository.findById(purchaseOrder.getPurchaseOrderUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(purchaseOrderData -> deletePurchaseOrderData(purchaseOrderData, permanent))
                .orElse(false);
    }

    public boolean deletePurchaseOrders(Set<String> purchaseOrderUuids, boolean permanent) {
        purchaseOrderUuids.forEach(purchaseOrderUuid ->
                purchaseOrderRepository.findById(purchaseOrderUuid)
                        .filter(entity -> !entity.getAuditLog().getDeleted())
                        .map(purchaseOrderData -> deletePurchaseOrderData(purchaseOrderData, permanent)));
        return true;
    }

    public List<PurchaseOrder> retrieveListByCondition(PurchaseOrderCondition condition, int offset, int limit) {
        return purchaseOrderRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public long countByCondition(PurchaseOrderCondition condition) {
        return purchaseOrderRepository.countByCondition(condition);
    }

}
