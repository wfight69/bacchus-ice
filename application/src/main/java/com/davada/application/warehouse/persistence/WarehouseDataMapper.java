package com.davada.application.warehouse.persistence;

import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.warehouse.persistence.data.WarehouseData;
import com.davada.domain.product.entity.Warehouse;
import com.davada.domain.wholesaler.vo.Location;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface WarehouseDataMapper {
    @Mapping(target = "version", ignore = true)
    WarehouseData toWarehouseData(Warehouse warehouse);
    Warehouse fromWarehouseData(WarehouseData warehouse);

    LocationData toLocationData(Location location);
}
