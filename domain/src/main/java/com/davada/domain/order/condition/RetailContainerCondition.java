package com.davada.domain.order.condition;

import com.davada.domain.order.vo.ContainerCollectType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailContainerCondition {
    String wholesalerUuid;
    ContainerCollectType containerCollectType;
    String employeeUuid;
    String salesManagerName;
    String startCollectDate;
    String endCollectDate;
    String retailShopCode;
    String retailShopName;
}
