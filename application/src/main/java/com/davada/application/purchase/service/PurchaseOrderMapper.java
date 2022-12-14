package com.davada.application.purchase.service;

import com.davada.application.purchase.service.port.PurchaseOrderCrudUseCase;
import com.davada.domain.purchase.entity.PurchaseOrder;
import com.davada.dto.purchase.PurchaseOrderDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PurchaseOrderMapper {
    @Mapping(target = "auditLog", ignore = true)
    @Mapping(target = "amount", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "vat", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "subtotalAmount", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "containerDeposit", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "bottleDeposit", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "totalAmount", expression = "java(BigDecimal.ZERO)")
    PurchaseOrder toDomain(String purchaseOrderUuid, PurchaseOrderCrudUseCase.CreatePurchaseOrderCommand command);

    PurchaseOrderDto toPurchaseOrderDto(PurchaseOrder purchaseOrder);
}
