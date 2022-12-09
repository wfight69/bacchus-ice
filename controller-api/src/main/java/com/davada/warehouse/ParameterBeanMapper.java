package com.davada.warehouse;

import com.davada.domain.product.condition.WarehouseCondition;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ParameterBeanMapper {

    @Mapping(target = "warehouseUuid", ignore = true)
    WarehouseCondition toWarehouseCondition(String wholesalerUuid, WarehouseQueryController.ParameterBean parameterBean);
}
