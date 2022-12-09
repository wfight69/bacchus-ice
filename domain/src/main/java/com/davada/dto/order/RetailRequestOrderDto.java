package com.davada.dto.order;

import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.YN;
import com.davada.domain.order.vo.RetailOrderChannel;
import com.davada.domain.order.vo.RetailRequestOrderStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailRequestOrderDto {
    String requestOrderUuid;
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    String employeeUuid;
    String employeeName;
    CodeName salesCourse;
    RetailOrderChannel retailOrderChannel;
    YN readYn;
    String orderDate;
    String orderTime;
    String retailOrderTelephone;
    RetailRequestOrderStatus retailRequestOrderStatus;
    String orderDescription;
    String voiceFileId;
}
