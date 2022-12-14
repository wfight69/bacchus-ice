package com.davada.application.purchase.persistence;

import com.davada.application.common.persistence.data.CodeNameData;
import com.davada.application.purchase.persistence.data.PurchaseOrderData;
import com.davada.application.purchase.persistence.data.PurchaseOrderItemData;
import com.davada.domain.common.vo.CodeName;

import com.davada.domain.purchase.entity.PurchaseOrder;
import com.davada.domain.purchase.entity.PurchaseOrderItem;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PurchaseOrderDataMapper {
    @Mapping(target = "version", ignore = true)
    PurchaseOrderData toData(PurchaseOrder purchaseOrder);

    PurchaseOrder fromData(PurchaseOrderData purchaseOrderData);

    @Mapping(target = "purchaseOrder", ignore = true)
    PurchaseOrderItemData toData(PurchaseOrderItem purchaseOrderItem);

    PurchaseOrderItem fromData(PurchaseOrderItemData purchaseOrderItemData);

    CodeNameData toCodeNameData(CodeName codeName);
}
