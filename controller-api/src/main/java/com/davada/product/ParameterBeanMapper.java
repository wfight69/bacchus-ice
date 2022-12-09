package com.davada.product;

import com.davada.domain.product.condition.ProductCondition;
import com.davada.domain.product.condition.WarehouseCondition;
import com.davada.warehouse.WarehouseQueryController;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ParameterBeanMapper {

    @Mapping(target = "retailShopUuid", ignore = true)
    ProductCondition toProductCondition(String wholesalerUuid, ProductQueryController.ProductParameterBean parameterBean);

    //ProductCondition toProductCondition(String wholesalerUuid, RetailProductQueryController.RetailProductParameterBean parameterBean);
}
