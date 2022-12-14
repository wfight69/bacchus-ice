package com.davada.application.purchase.persistence.data;

import com.davada.application.common.persistence.data.UnitPriceData;
import com.davada.application.purchase.persistence.PurchaseOrderDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "purchase_order_item")
public class PurchaseOrderItemData {
    @Id
    private String purchaseOrderItemUuid;
    private String productUuid;
    private String productName;
    private String productCode;
    private String volume;
    private Integer bottlesInBox;
    @Embedded
    private UnitPriceData containerPrice;
    private Integer containerQuantity;
    private Integer bottleQuantity;
    private Integer supplementQuantity;
    private BigDecimal amount;
    private BigDecimal vat;
    private BigDecimal subtotalAmount;
    private BigDecimal containerDeposit;
    private BigDecimal bottleDeposit;
    private BigDecimal totalAmount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_uuid", nullable = false)
    private PurchaseOrderData purchaseOrder;

    public boolean updateValues(NameValuePairs nameValuePairs, PurchaseOrderDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("containerQuantity",
                    value -> this.containerQuantity = Integer.parseInt(value));
            nameValuePairs.pullOut("bottleQuantity",
                    value -> this.bottleQuantity = Integer.parseInt(value));
            nameValuePairs.pullOut("supplementQuantity",
                    value -> this.supplementQuantity = Integer.parseInt(value));
            nameValuePairs.pullOut("amount",
                    value -> this.amount = new BigDecimal(value));
            nameValuePairs.pullOut("vat",
                    value -> this.vat = new BigDecimal(value));
            nameValuePairs.pullOut("subtotalAmount",
                    value -> this.subtotalAmount = new BigDecimal(value));
            nameValuePairs.pullOut("containerDeposit",
                    value -> this.containerDeposit = new BigDecimal(value));
            nameValuePairs.pullOut("bottleDeposit",
                    value -> this.bottleDeposit = new BigDecimal(value));
            nameValuePairs.pullOut("totalAmount",
                    value -> this.totalAmount = new BigDecimal(value));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
