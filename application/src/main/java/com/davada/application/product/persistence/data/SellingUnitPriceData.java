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
@Table(name = "selling_unit_price")
public class SellingUnitPriceData {
    @Id
    private String sellingUnitPriceUuid;
    private String productUuid;
    private BigDecimal generalProfitMarginRate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price",
                    column = @Column(name = "general_container_price")),
            @AttributeOverride(name = "vat",
                    column = @Column(name = "general_container_vat")),
            @AttributeOverride(name = "subtotal",
                    column = @Column(name = "general_container_subtotal")),
    })
    private UnitPriceData generalContainerPrice;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price",
                    column = @Column(name = "general_bottle_price")),
            @AttributeOverride(name = "vat",
                    column = @Column(name = "general_bottle_vat")),
            @AttributeOverride(name = "subtotal",
                    column = @Column(name = "general_bottle_subtotal")),
    })
    private UnitPriceData generalBottlePrice;
    private BigDecimal entProfitMarginRate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price",
                    column = @Column(name = "ent_container_price")),
            @AttributeOverride(name = "vat",
                    column = @Column(name = "ent_container_vat")),
            @AttributeOverride(name = "subtotal",
                    column = @Column(name = "ent_container_subtotal")),
    })
    private UnitPriceData entContainerPrice;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price",
                    column = @Column(name = "ent_bottle_price")),
            @AttributeOverride(name = "vat",
                    column = @Column(name = "ent_bottle_vat")),
            @AttributeOverride(name = "subtotal",
                    column = @Column(name = "ent_bottle_subtotal")),
    })
    private UnitPriceData entBottlePrice;
    private String applyStartDate;
    private String applyEndDate;
    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, ProductDataMapper jpaMapper) {

        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("generalProfitMarginRate",
                    value -> this.generalProfitMarginRate = new BigDecimal(value));
            nameValuePairs.pullOut("generalContainerPrice",
                    value -> this.generalContainerPrice = jpaMapper.toUnitPriceData(fromJson(value, UnitPrice.class)));
            nameValuePairs.pullOut("generalBottlePrice",
                    value -> this.generalBottlePrice = jpaMapper.toUnitPriceData(fromJson(value, UnitPrice.class)));
            nameValuePairs.pullOut("entProfitMarginRate",
                    value -> this.entProfitMarginRate = new BigDecimal(value));
            nameValuePairs.pullOut("entContainerPrice",
                    value -> this.entContainerPrice = jpaMapper.toUnitPriceData(fromJson(value, UnitPrice.class)));
            nameValuePairs.pullOut("entBottlePrice",
                    value -> this.entBottlePrice = jpaMapper.toUnitPriceData(fromJson(value, UnitPrice.class)));
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
