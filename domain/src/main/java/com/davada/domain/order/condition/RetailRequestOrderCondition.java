package com.davada.domain.order.condition;

import com.davada.domain.order.vo.RetailOrderChannel;
import com.davada.domain.order.vo.RetailRequestOrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailRequestOrderCondition {
    String wholesalerUuid;
    RetailOrderChannel retailOrderChannel;
    RetailRequestOrderStatus retailRequestOrderStatus;
    String startOrderDate;
    String endOrderDate;
    String salesCourseCode;
    String salesManagerName;
    String retailShopCode;
    String retailShopName;
}
