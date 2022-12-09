package com.davada.domain.order.condition;

import com.davada.domain.order.vo.PurchaseType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseOrderCondition {
    String wholesalerUuid;
    PurchaseType purchaseType;
    String startOrderDate;
    String endOrderDate;
    String supplierCode;
    String supplierName;
}
