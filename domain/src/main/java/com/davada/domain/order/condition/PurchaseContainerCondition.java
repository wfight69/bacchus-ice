package com.davada.domain.order.condition;

import com.davada.domain.order.vo.ContainerReturnType;
import com.davada.domain.order.vo.TransportType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseContainerCondition {
    String wholesalerUuid;
    ContainerReturnType containerReturnType;
    TransportType transportType;
    String startReturnDate;
    String endReturnDate;
    String supplierCode;
    String supplierName;
}
