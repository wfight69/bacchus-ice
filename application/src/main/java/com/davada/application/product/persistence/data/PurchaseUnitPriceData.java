package com.davada.application.product.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.common.persistence.data.UnitPriceData;
import com.davada.application.product.persistence.ProductDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.product.vo.UnitPrice;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Entity
@Table(name = "purchase_unit_price")
public class PurchaseUnitPriceData {
    @Id
    private String purchaseUnitPriceUuid;
    private String productUuid;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price",
                    column = @Column(name = "container_price")),
            @AttributeOverride(name = "vat",
                    column = @Column(name = "container_vat")),
            @AttributeOverride(name = "subtotal",
                    column = @Column(name = "container_subtotal")),
    })
    private UnitPriceData containerPrice;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price",
                    column = @Column(name = "bottle_price")),
            @AttributeOverride(name = "vat",
                    column = @Column(name = "bottle_vat")),
            @AttributeOverride(name = "subtotal",
                    column = @Column(name = "bottle_subtotal")),
    })
    private UnitPriceData bottlePrice;
    private BigDecimal totalPrice;
    private String applyStartDate;
    private String applyEndDate;
    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, ProductDataMapper jpaMapper) {

        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("totalPrice",
                    value -> this.totalPrice = new BigDecimal(value));
            nameValuePairs.pullOut("containerPrice",
                    value -> this.containerPrice = jpaMapper.toUnitPriceData(fromJson(value, UnitPrice.class)));
            nameValuePairs.pullOut("bottlePrice",
                    value -> this.bottlePrice = jpaMapper.toUnitPriceData(fromJson(value, UnitPrice.class)));
            nameValuePairs.pullOut("applyStartDate",
                    value -> this.applyStartDate = trim(value));
            nameValuePairs.pullOut("applyEndDate",
                    value -> this.applyEndDate = trim(value));
            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;

    }
}
