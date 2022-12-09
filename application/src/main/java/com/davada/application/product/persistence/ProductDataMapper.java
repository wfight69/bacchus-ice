package com.davada.application.product.persistence;

import com.davada.application.common.persistence.data.UnitPriceData;
import com.davada.application.product.persistence.data.ProductData;
import com.davada.application.product.persistence.data.PurchaseUnitPriceData;
import com.davada.application.product.persistence.data.SellingUnitPriceData;
import com.davada.domain.product.entity.*;
import com.davada.domain.product.vo.UnitPrice;

import org.mapstruct.*;


@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductDataMapper {

    @Mapping(target = "version", ignore = true)
    ProductData toProductData(Product product);

    Product fromProductData(ProductData productData);

    @Mapping(target = "version", ignore = true)
    PurchaseUnitPriceData toPurchaseUnitPriceData(ProductPurchaseUnitPrice purchaseUnitPrice);
    ProductPurchaseUnitPrice fromPurchaseUnitPriceData(PurchaseUnitPriceData purchaseUnitPrice);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "applyStartDate", ignore = true)
    @Mapping(target = "applyEndDate", ignore = true)
    SellingUnitPriceData toSellingUnitPriceData(ProductSellingBaseUnitPrice sellingUnitPrice);
    ProductSellingBaseUnitPrice fromSellingUnitPriceData(SellingUnitPriceData sellingUnitPrice);

    UnitPriceData toUnitPriceData(UnitPrice unitPrice);
}
