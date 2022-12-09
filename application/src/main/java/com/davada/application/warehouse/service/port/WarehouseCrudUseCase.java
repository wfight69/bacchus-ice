package com.davada.application.warehouse.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.product.entity.Warehouse;
import com.davada.domain.product.vo.WarehouseType;
import com.davada.domain.wholesaler.vo.Location;
import com.davada.dto.product.WarehouseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface WarehouseCrudUseCase {
    IdValue createWarehouse(Warehouse warehouse);

    WarehouseDto retrieveWarehouse(String warehouseUuid);

    List<WarehouseDto> retrieveAllWarehouse(String wholesalerUuid);

    BooleanValue updateWarehouse(String warehouseUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteWarehouse(String warehouseUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateWarehouseCommand {
        String wholesalerUuid;
        String warehouseCode;
        String warehouseName;
        Location location;
        String telephone;
        String employeeUuid;
        String employeeName;
        String supplierUuid;
        String supplierName;
        WarehouseType warehouseType;
        String description;
    }
}
