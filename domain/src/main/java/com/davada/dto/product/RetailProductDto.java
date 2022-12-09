package com.davada.dto.product;

import com.davada.domain.product.vo.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailProductDto {
    String productUuid;
    String productCode;
    String productName;
    String productAliasName;
    ProductPricePolicy productPricePolicy;
    RetailProductUnitPriceDto productUnitPrice;
    RetailBeverageContainerDto beverageContainer;
    String barcode;
    String boxBarcode;
    String bottleBarcode;
    String supplierUuid;
    String supplierCode;
    String supplierName;
    ProductCategory productCategory;
    BeverageCategory beverageCategory;
    String volume;
    Integer bottlesInBox;
    String alcoholByVolume;
    Integer stockQuantity;
    Integer safetyStock;
    StockCountingMethod stockCountingMethod;
    KeyProduct keyProduct;
    String rfidProductName;
    String rfidBoxTag;
    String rfidEaTag;
    String description;
}
