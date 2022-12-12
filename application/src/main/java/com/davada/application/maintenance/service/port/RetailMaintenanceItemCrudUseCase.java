package com.davada.application.maintenance.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;

import com.davada.dto.maintenance.RetailMaintenanceItemDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public interface RetailMaintenanceItemCrudUseCase {
    IdValue createMaintenanceItem(CreateMaintenanceItemCommand command);

    RetailMaintenanceItemDto retrieveMaintenanceItem(String maintenanceItemUuid);

    List<RetailMaintenanceItemDto> retrieveAllMaintenanceItem(String maintenanceUuid);

    BooleanValue updateMaintenanceItem(String maintenanceItemUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteMaintenanceItem(String maintenanceItemUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreateMaintenanceItemCommand {
        String maintenanceItemUuid;
        String productUuid;
        String productName;
        String productCode;
        Integer quantity;
        BigDecimal amount;
        BigDecimal vat;
        BigDecimal subtotalAmount;
        BigDecimal totalAmount;
    }
}
