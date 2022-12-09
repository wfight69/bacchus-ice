package com.davada.dto.order;

import com.davada.domain.common.vo.BusinessCategory;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.YN;
import com.davada.domain.order.vo.CalculateStatus;
import com.davada.domain.order.vo.RetailOrderChannel;
import com.davada.domain.order.vo.RetailOrderStatus;
import com.davada.domain.order.vo.SalesType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailOrderDto {
    String orderUuid;
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    BusinessCategory businessCategory;
    String employeeUuid;
    String employeeName;
    String employeeCode;
    CodeName salesCourse;
    String warehouseUuid;
    String warehouseCode;
    String warehouseName;
    YN invoiceIssueYn;
    YN vatYn;
    CalculateStatus calculateStatus;
    SalesType salesType;
    String orderDate;
    String orderTime;
    String orderCreateDate;
    String orderCreateTime;
    String productShortName;
    Integer containerQuantity;
    Integer bottleQuantity;
    BigDecimal amount;
    BigDecimal vat;
    BigDecimal subtotalAmount;
    BigDecimal containerDeposit;
    BigDecimal bottleDeposit;
    BigDecimal totalAmount;
    RetailOrderStatus retailOrderStatus;
    String description;
    Set<RetailOrderItemDto> orderItems;
    //---------
    RetailOrderChannel retailOrderChannel;
    YN readYn;
    YN registerOrderYn;
    String retailOrderTelephone;
    String orderDescription;
    String voiceFileId;
}
