package com.davada.application.purchase.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.common.vo.YN;
import com.davada.domain.order.vo.CalculateStatus;
import com.davada.domain.order.vo.RetailBeverageContainer;
import com.davada.domain.product.vo.UnitPrice;

import com.davada.domain.purchase.vo.PurchaseType;
import com.davada.dto.purchase.PurchaseOrderDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface PurchaseOrderCrudUseCase {

    IdValue createPurchaseOrder(CreatePurchaseOrderCommand command);

    PurchaseOrderDto retrievePurchaseOrder(String purchaseOrderUuid);

    List<PurchaseOrderDto> retrieveAllPurchaseOrder(String wholesalerUuid);

    BooleanValue updatePurchaseOrder(String purchaseOrderUuid, NameValuePairs nameValuePairs);

    BooleanValue deletePurchaseOrder(String purchaseOrderUuid, boolean permanent);

    BooleanValue deletePurchaseOrders(DeletePurchaseOrderCommand command);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreatePurchaseOrderCommand {
        @NotBlank(message = "주류도매 아이디는 필수 입력입니다.")
        String wholesalerUuid;
        String supplierUuid;
        String supplierCode;
        String supplierName;
        String supplierRepresentativeName;
        String businessNumber;
        String warehouseUuid;
        String warehouseCode;
        String warehouseName;
        YN tradingStatementYn;
        YN vatYn;
        CalculateStatus calculateStatus;
        PurchaseType purchaseType;
        String orderDate;
        String orderTime;
        String productShortName;
        Integer quantity;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal vat = BigDecimal.ZERO;
        BigDecimal subtotalAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        String description;
        @Valid Set<CreatePurchaseOrderItemCommand> purchaseOrderItems;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreatePurchaseOrderItemCommand {
        String purchaseOrderItemUuid;
        @NotBlank(message = "상품 아이디는 필수 입력입니다.")
        String productUuid;
        String productName;
        String productCode;
        String volume;
        Integer bottlesInBox;
        UnitPrice containerPrice;
        @NotNull Integer quantity;
        @NotNull BigDecimal amount;
        @NotNull BigDecimal vat;
        @NotNull BigDecimal subtotalAmount;
        @NotNull BigDecimal totalAmount;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class DeletePurchaseOrderCommand {
        Set<String> purchaseOrderUuids;
        boolean permanent;
    }
}
