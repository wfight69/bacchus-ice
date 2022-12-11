package com.davada.application.maintenance.persistence.data;

import com.davada.order.adapter.out.persistence.RetailOrderItemDataMapper;
import com.davada.shared.adapter.persistence.data.UnitPriceData;
import com.davada.shared.domain.common.entity.NameValuePairs;
import com.davada.shared.domain.common.exception.ErpCannotModifyPropertyException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "retail_maintenance_item")
public class RetailMaintenanceItemData {
    @Id
    private String orderItemUuid;
    private String productUuid;
    private String productName;
    private String productCode;
    private String rfidBoxTag;
    private String rfidEaTag;
    private String volume;
    private Integer bottlesInBox;
    //
    private Boolean isContractPrice;
    private BigDecimal profitMarginRate;
    @AttributeOverrides({
            @AttributeOverride(name = "price",
                    column = @Column(name = "container_price")),
            @AttributeOverride(name = "vat",
                    column = @Column(name = "container_vat")),
            @AttributeOverride(name = "subtotal",
                    column = @Column(name = "container_subtotal")),
    })
    @Embedded
    private UnitPriceData containerPrice;
    private Integer containerQuantity;
    private Integer bottleQuantity;
    private BigDecimal amount;
    private BigDecimal vat;
    private BigDecimal subtotalAmount;
    private BigDecimal containerDeposit;
    private BigDecimal bottleDeposit;
    private BigDecimal totalAmount;

    // RFID 목록

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_uuid", nullable = false)
    private RetailMaintenanceData order;

    public boolean updateValues(NameValuePairs nameValuePairs, RetailOrderItemDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("containerQuantity",
                    value -> this.containerQuantity = Integer.parseInt(value));
            nameValuePairs.pullOut("bottleQuantity",
                    value -> this.bottleQuantity = Integer.parseInt(value));
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
