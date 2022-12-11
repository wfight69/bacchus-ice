package com.davada.domain.maintenance.condition;

import com.davada.domain.maintenance.vo.MaintenanceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailMaintenanceCondition {
    String wholesalerUuid;
    MaintenanceType maintenanceType;
    String employeeUuid;
    String startOrderDate;
    String endOrderDate;
    String startOrderCreateDate;
    String endOrderCreateDate;
    String salesCourseCode;
    String salesManagerName;
    String retailShopCode;
    String retailShopName;
}
