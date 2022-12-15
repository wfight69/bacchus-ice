package com.davada.application.purchase.persistence;

import com.davada.application.purchase.persistence.data.PurchaseOrderItemData;
import com.davada.domain.purchase.entity.PurchaseOrderItem;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PurchaseOrderItemDataMapper {
    @Mapping(target = "purchaseOrder", ignore = true)
    PurchaseOrderItemData toData(PurchaseOrderItem purchaseOrderItem);

    PurchaseOrderItem fromData(PurchaseOrderItemData purchaseOrderItemData);
}
