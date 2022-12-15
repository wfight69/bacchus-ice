package com.davada.application.purchase.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.order.vo.RetailBeverageContainer;
import com.davada.domain.product.vo.UnitPrice;

import com.davada.dto.purchase.PurchaseOrderItemDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseOrderItemCrudUseCase {
    IdValue createPurchaseOrderItem(CreatePurchaseOrderItemCommand command);

    PurchaseOrderItemDto retrievePurchaseOrderItem(String purchaseOrderItemUuid);

    List<PurchaseOrderItemDto> retrieveAllPurchaseOrderItem(String purchaseOrderUuid);

    BooleanValue updatePurchaseOrderItem(String purchaseOrderItemUuid, NameValuePairs nameValuePairs);

    BooleanValue deletePurchaseOrderItem(String purchaseOrderItemUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreatePurchaseOrderItemCommand {
        String productUuid;
        String productName;
        String productCode;
        String volume;
        Integer bottlesInBox;
        RetailBeverageContainer beverageContainer;
        UnitPrice containerPrice;
        Integer quantity;
        BigDecimal amount;
        BigDecimal vat;
        BigDecimal subtotalAmount;
        BigDecimal totalAmount;
    }
}
