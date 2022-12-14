package com.davada.application.purchase.service;

import com.davada.application.purchase.service.port.PurchaseOrderItemCrudUseCase;
import com.davada.domain.purchase.entity.PurchaseOrderItem;
import com.davada.dto.purchase.PurchaseOrderItemDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PurchaseOrderItemMapper {
    PurchaseOrderItem toDomain(String purchaseOrderItemUuid, PurchaseOrderItemCrudUseCase.CreatePurchaseOrderItemCommand command);

    PurchaseOrderItemDto toDto(PurchaseOrderItem purchaseOrderItem);
}
