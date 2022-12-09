package com.davada.application.product.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.product.persistence.ProductDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.product.entity.ProductPurchaseUnitPrice;
import com.davada.domain.product.entity.ProductSellingBaseUnitPrice;
import com.davada.domain.product.vo.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Entity
@Table(name = "product")
public class ProductData {
    @Id
    private String productUuid;
    private String wholesalerUuid;

    private String productCode;
    private String productName;
    private String productAliasName;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "purchase_unit_price_uuid")
    private PurchaseUnitPriceData purchaseUnitPrice;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sales_unit_price_uuid")
    private SellingUnitPriceData sellingUnitPrice;
//    @ManyToOne
//    @JoinColumn(name = "beverage_container_uuid")
//    private BeverageContainerData beverageContainer;
    private String barcode;
    private String boxBarcode;
    private String bottleBarcode;
    private String supplierUuid;
    private String supplierCode;
    private String supplierName;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    private BeverageCategory beverageCategory;
    @Enumerated(EnumType.STRING)
    private ProductPricePolicy productPricePolicy;
    private String volume;
    private Integer bottlesInBox;
    private String alcoholByVolume;
    private Integer stockQuantity;
    private Integer safetyStock;
    @Enumerated(EnumType.STRING)
    private StockCountingMethod stockCountingMethod;
    @Enumerated(EnumType.STRING)
    private KeyProduct keyProduct;
    private String rfidProductName;
    private String rfidBoxTag;
    private String rfidEaTag;
    private String description;
    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, ProductDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("productCode",
                    value -> this.productCode = trim(value));
            nameValuePairs.pullOut("productName",
                    value -> this.productName = trim(value));
            nameValuePairs.pullOut("productAliasName",
                    value -> this.productAliasName = trim(value));
            nameValuePairs.pullOut("purchaseUnitPrice",
                    value -> {
                        ProductPurchaseUnitPrice purchaseUnitPriceDomain = fromJson(value, ProductPurchaseUnitPrice.class);
                        purchaseUnitPriceDomain.setPurchaseUnitPriceUuid(ErpId.newId().getUuid().toString());
                        purchaseUnitPriceDomain.refineValues();
                        this.purchaseUnitPrice = jpaMapper.toPurchaseUnitPriceData(purchaseUnitPriceDomain);
                    });
            nameValuePairs.pullOut("sellingUnitPrice",
                    value -> {
                        ProductSellingBaseUnitPrice sellingUnitPriceDomain = fromJson(value, ProductSellingBaseUnitPrice.class);
                        sellingUnitPriceDomain.setSellingUnitPriceUuid(ErpId.newId().getUuid().toString());
                        sellingUnitPriceDomain.refineValues();
                        this.sellingUnitPrice = jpaMapper.toSellingUnitPriceData(sellingUnitPriceDomain);
                    });
            nameValuePairs.pullOut("barcode",
                    value -> this.barcode = trim(value));
            nameValuePairs.pullOut("boxBarcode",
                    value -> this.boxBarcode = trim(value));
            nameValuePairs.pullOut("bottleBarcode",
                    value -> this.bottleBarcode = trim(value));
            nameValuePairs.pullOut("supplierUuid",
                    value -> this.supplierUuid = trim(value));
            nameValuePairs.pullOut("productCategory",
                    value -> this.productCategory = ProductCategory.valueOf(value));
            nameValuePairs.pullOut("beverageCategory",
                    value -> this.beverageCategory = BeverageCategory.valueOf(value));
            nameValuePairs.pullOut("productPricePolicy",
                    value -> this.productPricePolicy = ProductPricePolicy.valueOf(value));
            nameValuePairs.pullOut("volume",
                    value -> this.volume = trim(value));
            nameValuePairs.pullOut("bottlesInBox",
                    value -> this.bottlesInBox = Integer.valueOf(value));
            nameValuePairs.pullOut("alcoholByVolume",
                    value -> this.alcoholByVolume = trim(value));
            nameValuePairs.pullOut("stockQuantity",
                    value -> this.stockQuantity = Integer.valueOf(value));
            nameValuePairs.pullOut("safetyStock",
                    value -> this.safetyStock = Integer.valueOf(value));
            nameValuePairs.pullOut("stockCountingMethod",
                    value -> this.stockCountingMethod = StockCountingMethod.valueOf(value));
            nameValuePairs.pullOut("keyProduct",
                    value -> this.keyProduct = KeyProduct.valueOf(value));
            nameValuePairs.pullOut("rfidProductName",
                    value -> this.rfidProductName = trim(value));
            nameValuePairs.pullOut("rfidBoxTag",
                    value -> this.rfidBoxTag = trim(value));
            nameValuePairs.pullOut("rfidEaTag",
                    value -> this.rfidEaTag = trim(value));
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
