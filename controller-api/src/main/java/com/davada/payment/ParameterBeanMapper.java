package com.davada.payment;

import com.davada.domain.payment.condition.DepositCondition;
import com.davada.domain.payment.condition.PaymentCondition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ParameterBeanMapper {
    DepositCondition toCondition(String wholesalerUuid, DepositQueryController.ParameterBean parameterBean);

    PaymentCondition toCondition(String wholesalerUuid, PaymentQueryController.ParameterBean parameterBean);
    
    //TaxInvoiceCondition toCondition(String wholesalerUuid, TaxInvoiceQueryController.ParameterBean parameterBean);
}
