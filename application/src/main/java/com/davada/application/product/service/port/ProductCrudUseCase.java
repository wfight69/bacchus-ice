package com.davada.application.product.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.product.entity.BeverageContainer;
import com.davada.domain.product.entity.Product;
import com.davada.domain.product.entity.ProductPurchaseUnitPrice;
import com.davada.domain.product.entity.ProductSellingBaseUnitPrice;
import com.davada.domain.product.vo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ProductCrudUseCase {
    IdValue createProduct(Product product);

    Product retrieveProduct(String productUuid);

    List<Product> retrieveAllProduct(String wholesalerUuid);

    BooleanValue updateProduct(String productUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteProduct(String productUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateProductCommand {
        String wholesalerUuid;
        String productCode;
        String productName;
        String productAliasName;
        ProductPurchaseUnitPrice purchaseUnitPrice;
        ProductSellingBaseUnitPrice sellingUnitPrice;
        BeverageContainer beverageContainer;
        String barcode;
        String boxBarcode;
        String bottleBarcode;
        String supplierUuid;
        ProductCategory productCategory;
        BeverageCategory beverageCategory;
        ProductPricePolicy productPricePolicy;
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
}
