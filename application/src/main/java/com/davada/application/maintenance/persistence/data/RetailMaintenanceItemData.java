package com.davada.application.maintenance.persistence.data;

import com.davada.application.maintenance.persistence.RetailMaintenanceItemDataMapper;
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
@Table(name = "retail_maintenance_item")
public class RetailMaintenanceItemData {
    @Id
    private String maintenanceItemUuid;
    private String productUuid;
    private String productCode;
    private String productName;
    //
    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal vat;
    private BigDecimal subtotalAmount;
    private BigDecimal totalAmount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_uuid", nullable = false)
    private RetailMaintenanceData maintenance;

    public boolean updateValues(NameValuePairs nameValuePairs, RetailMaintenanceItemDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
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

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
