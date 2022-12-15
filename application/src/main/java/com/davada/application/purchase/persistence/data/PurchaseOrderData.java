package com.davada.application.purchase.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.purchase.persistence.PurchaseOrderDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.YN;
import com.davada.domain.order.vo.CalculateStatus;

import com.davada.domain.purchase.vo.PurchaseType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "purchase_order")
public class PurchaseOrderData {
    @Id
    String purchaseOrderUuid;
    String wholesalerUuid;
    String supplierUuid;
    String supplierCode;
    String supplierName;
    String supplierRepresentativeName;
    String businessNumber;
    String warehouseUuid;
    String warehouseCode;
    String warehouseName;
    @Enumerated(EnumType.STRING)
    YN tradingStatementYn;
    @Enumerated(EnumType.STRING)
    YN vatYn;
    @Enumerated(EnumType.STRING)
    CalculateStatus calculateStatus;
    @Enumerated(EnumType.STRING)
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
    @OneToMany(mappedBy = "purchaseOrder", orphanRemoval = true)
    Set<PurchaseOrderItemData> purchaseOrderItems = new LinkedHashSet<>();

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;


    private void updateProductShortName() {
        this.productShortName = purchaseOrderItems.stream().findFirst()
                .map(purchaseOrderItem -> purchaseOrderItem.getProductName() + " 외 " + purchaseOrderItems.size() + "건")
                .orElse("");
    }

    public void reCalculateOrder() {
        quantity = 0;
        amount = BigDecimal.ZERO;
        vat = BigDecimal.ZERO;
        subtotalAmount = BigDecimal.ZERO;
        totalAmount = BigDecimal.ZERO;

        purchaseOrderItems.forEach(purchaseOrderItem -> {
            quantity = quantity + purchaseOrderItem.getQuantity();
            amount = amount.add(purchaseOrderItem.getAmount());
            vat = vat.add(purchaseOrderItem.getVat());
            subtotalAmount = subtotalAmount.add(purchaseOrderItem.getSubtotalAmount());
            totalAmount = totalAmount.add(purchaseOrderItem.getTotalAmount());
        });
        updateProductShortName();
    }

    public boolean updateValues(NameValuePairs nameValuePairs, PurchaseOrderDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("supplierUuid",
                    value -> this.supplierUuid = trim(value));
            nameValuePairs.pullOut("supplierCode",
                    value -> this.supplierCode = trim(value));
            nameValuePairs.pullOut("supplierName",
                    value -> this.supplierName = trim(value));
            nameValuePairs.pullOut("supplierRepresentativeName",
                    value -> this.supplierRepresentativeName = trim(value));
            nameValuePairs.pullOut("businessNumber",
                    value -> this.businessNumber = trim(value));
            nameValuePairs.pullOut("warehouseUuid",
                    value -> this.warehouseUuid = trim(value));
            nameValuePairs.pullOut("warehouseCode",
                    value -> this.warehouseCode = trim(value));
            nameValuePairs.pullOut("warehouseName",
                    value -> this.warehouseName = trim(value));
            nameValuePairs.pullOut("tradingStatementYn",
                    value -> this.tradingStatementYn = YN.valueOf(value));
            nameValuePairs.pullOut("vatYn",
                    value -> this.vatYn = YN.valueOf(value));
            nameValuePairs.pullOut("calculateStatus",
                    value -> this.calculateStatus = CalculateStatus.valueOf(value));
            nameValuePairs.pullOut("purchaseType",
                    value -> this.purchaseType = PurchaseType.valueOf(value));
            nameValuePairs.pullOut("orderDate",
                    value -> this.orderDate = trim(value));
            nameValuePairs.pullOut("orderTime",
                    value -> this.orderTime = trim(value));
            nameValuePairs.pullOut("productShortName",
                    value -> this.productShortName = trim(value));
            nameValuePairs.pullOut("quantity",
                    value -> this.quantity = Integer.parseInt(value));
            nameValuePairs.pullOut("amount",
                    value -> this.amount = new BigDecimal(value));
            nameValuePairs.pullOut("vat",
                    value -> this.vat = new BigDecimal(value));
            nameValuePairs.pullOut("subtotalAmount",
                    value -> this.subtotalAmount = new BigDecimal(value));
            nameValuePairs.pullOut("totalAmount",
                    value -> this.totalAmount = new BigDecimal(value));
            nameValuePairs.pullOut("description",
                    value -> this.description = trim(value));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }

}
