package com.davada.application.payment.service;

import com.davada.application.payment.service.port.PaymentCrudUseCase;
import com.davada.domain.payment.entity.Payment;
import com.davada.dto.payment.PaymentDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PaymentMapper {
    @Mapping(target = "auditLog", ignore = true)
    @Mapping(target = "closeYn", expression = "java(YN.N)")
    Payment toDomain(String paymentUuid, PaymentCrudUseCase.CreatePaymentCommand command);

    PaymentDto toPaymentDto(Payment payment);
}
