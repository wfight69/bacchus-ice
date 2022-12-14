package com.davada.application.purchase.persistence.repository;

import com.davada.application.purchase.persistence.data.PurchaseOrderData;
import com.davada.application.purchase.persistence.data.PurchaseOrderItemData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItemData, String> {
    long deleteByPurchaseOrder(PurchaseOrderData purchaseOrderData);

    List<PurchaseOrderItemData> findByPurchaseOrder(PurchaseOrderData purchaseOrderData);
}
