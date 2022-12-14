package com.davada.application.payment.persistence;

import com.davada.application.payment.persistence.data.PaymentData;
import com.davada.domain.payment.entity.Payment;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PaymentDataMapper {
    @Mapping(target = "version", ignore = true)
    PaymentData toData(Payment purchasePayment);

    Payment fromData(PaymentData orderPaymentData);
}
