package com.davada.application.product.service;

import com.davada.application.product.service.port.ProductCrudUseCase;
import com.davada.domain.product.entity.Product;
import com.davada.dto.product.RetailProductDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductDomainMapper {
    @Mapping(target = "auditLog", ignore = true)
    @Mapping(target = "supplierCode", ignore = true)
    @Mapping(target = "supplierName", ignore = true)
    Product toDomain(String productUuid, ProductCrudUseCase.CreateProductCommand command);

    @Mapping(target = "productUnitPrice", ignore = true)
    RetailProductDto toProductDto(Product product);

//    @Mapping(target = "sellingContainerDeposit", source = "beverageContainer.containerDeposit.sellingDeposit")
//    @Mapping(target = "sellingBottleDeposit", source = "beverageContainer.bottleDeposit.sellingDeposit")
//    RetailBeverageContainerDto toRetailBeverageContainerDto(BeverageContainer beverageContainer);
//
//    @Mapping(target = "isContractPrice", constant = "false")
//    @Mapping(target = "profitMarginRate", source = "sellingUnitPrice.generalProfitMarginRate")
//    @Mapping(target = "containerPrice", source = "sellingUnitPrice.generalContainerPrice")
//    @Mapping(target = "bottlePrice", source = "sellingUnitPrice.generalBottlePrice")
//    RetailProductUnitPriceDto toGeneralRetailProductUnitPriceDto(ProductSellingBaseUnitPrice sellingUnitPrice);
//    @Mapping(target = "isContractPrice", constant = "false")
//    @Mapping(target = "profitMarginRate", source = "sellingUnitPrice.entProfitMarginRate")
//    @Mapping(target = "containerPrice", source = "sellingUnitPrice.entContainerPrice")
//    @Mapping(target = "bottlePrice", source = "sellingUnitPrice.entBottlePrice")
//    RetailProductUnitPriceDto toEntRetailProductUnitPriceDto(ProductSellingBaseUnitPrice sellingUnitPrice);
//    @Mapping(target = "isContractPrice", constant = "true")
//    RetailProductUnitPriceDto toSpecificRetailProductUnitPriceDto(ProductSellingContractUnitPrice sellingSpecificUnitPrice);
}
