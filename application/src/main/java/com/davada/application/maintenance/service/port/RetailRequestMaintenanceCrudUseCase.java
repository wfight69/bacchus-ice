package com.davada.application.maintenance.service.port;

import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.maintenance.vo.RetailMaintenanceChannel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public interface RetailRequestMaintenanceCrudUseCase {
    IdValue createRequestMaintenance(CreateRequestMaintenanceCommand command);

    BooleanValue deleteRequestMaintenance(String requestMaintenanceUuid, boolean permanent);

    BooleanValue deleteRequestMaintenances(DeleteRequestMaintenanceCommand command);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreateRequestMaintenanceCommand {
        @NotBlank
        String icesalerUuid;
        String wholesalerUuid;
        String retailShopUuid;
        String retailShopCode;
        String retailShopName;
        MaintenanceType maintenanceType;
        RetailMaintenanceChannel retailMaintenanceChannel;
        String createDescription;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class DeleteRequestMaintenanceCommand {
        Set<String> requestMaintenanceUuids;
        boolean permanent;
    }
}
