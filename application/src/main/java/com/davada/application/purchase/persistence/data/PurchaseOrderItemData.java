package com.davada.application.purchase.persistence.data;

import com.davada.application.common.persistence.data.UnitPriceData;
import com.davada.application.purchase.persistence.PurchaseOrderItemDataMapper;
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
    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal itemVat;
    private BigDecimal totalAmount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_uuid", nullable = false)
    private PurchaseOrderData purchaseOrder;

    public boolean updateValues(NameValuePairs nameValuePairs, PurchaseOrderItemDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("quantity",
                    value -> this.quantity = Integer.parseInt(value));
            nameValuePairs.pullOut("amount",
                    value -> this.amount = new BigDecimal(value));
            nameValuePairs.pullOut("itemVat",
                    value -> this.itemVat = new BigDecimal(value));
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
