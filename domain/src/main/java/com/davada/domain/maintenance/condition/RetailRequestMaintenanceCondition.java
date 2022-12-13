package com.davada.domain.maintenance.condition;

import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.maintenance.vo.RetailMaintenanceChannel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailRequestMaintenanceCondition {
    String wholesalerUuid;
    MaintenanceType maintenanceType;
    RetailMaintenanceChannel retailMaintenanceChannel;
    //RetailRequestMaintenanceStatus retailRequestMaintenanceStatus;
    String startMaintenanceDate;
    String endMaintenanceDate;
    String salesCourseCode;
    String salesManagerName;
    String retailShopCode;
    String retailShopName;
}
