package com.davada.domain.order.condition;

import com.davada.domain.order.vo.SalesType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailOrderCondition {
    String wholesalerUuid;
    SalesType salesType;
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
