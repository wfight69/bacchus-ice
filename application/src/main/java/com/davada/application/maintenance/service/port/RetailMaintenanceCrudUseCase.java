package com.davada.application.maintenance.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.*;
import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.maintenance.vo.RetailMaintenanceChannel;
import com.davada.domain.maintenance.vo.RetailMaintenanceStatus;
import com.davada.domain.order.vo.CalculateStatus;
import com.davada.domain.product.vo.UnitPrice;
import com.davada.dto.maintenance.RetailMaintenanceDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface RetailMaintenanceCrudUseCase {

    IdValue createMaintenance(CreateMaintenanceCommand command);

    RetailMaintenanceDto retrieveMaintenance(String maintenanceUuid);

    List<RetailMaintenanceDto> retrieveAllMaintenance(String wholesalerUuid);

    BooleanValue updateMaintenance(String maintenanceUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteMaintenance(String maintenanceUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreateMaintenanceCommand {
        String maintenanceUuid;
        String icesalerUuid;
        String wholesalerUuid;
        String wholesalerName;
        String wholesalerCeoTelephone;
        String retailShopUuid;
        String retailShopCode;
        String retailShopName;
        String createDate;
        String createTime;
        String createDescription;
        String productShortName;
        String acceptDate;
        String acceptTime;
        String employeeUuid;
        String employeeCode;
        String employeeName;
        String acceptDescription;
        String maintenanceDate;
        String maintenanceTime;
        String maintenanceEmployeeUuid;
        String maintenanceEmployeeCode;
        String maintenanceEmployeeName;
        String maintenanceDescription;
        String approvalDate;
        String approvalTime;
        String approvalEmployeeUuid;
        String approvalEmployeeCode;
        String approvalEmployeeName;
        String approvalDescription;
        String warehouseUuid;
        String warehouseCode;
        String warehouseName;
        CalculateStatus calculateStatus;
        MaintenanceType maintenanceType;
        RetailMaintenanceStatus retailMaintenanceStatus;
        RetailMaintenanceChannel retailMaintenanceChannel;
        //
        Integer quantity;
        BigDecimal amount;
        BigDecimal vat;
        BigDecimal subtotalAmount;
        BigDecimal totalAmount;
        // ???????????? ??????
        @Valid Set<RetailMaintenanceItemCommand> maintenanceItems;

        // ?????????(?????????????????????) ??????/?????????
        YN invoiceIssueYn;
        // ??????-???????????? ??????
        String description;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class RetailMaintenanceItemCommand {
        String maintenanceItemUuid;
        @NotBlank(message = "?????? ???????????? ?????? ???????????????.")
        String productUuid;
        String productName;
        String productCode;
        UnitPrice containerPrice;
        @NotNull Integer quantity;
        @NotNull BigDecimal amount;
        @NotNull BigDecimal vat;
        @NotNull BigDecimal subtotalAmount;
        @NotNull BigDecimal totalAmount;
    }
}
