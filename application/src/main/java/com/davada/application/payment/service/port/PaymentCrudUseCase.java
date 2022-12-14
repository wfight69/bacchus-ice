package com.davada.application.payment.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.payment.vo.VatType;

import com.davada.dto.payment.PaymentDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

public interface PaymentCrudUseCase {

    IdValue createPayment(CreatePaymentCommand command);

    PaymentDto retrievePayment(String paymentUuid);

    List<PaymentDto> retrieveAllPayment(String wholesalerUuid);

    BooleanValue updatePayment(String paymentUuid, NameValuePairs nameValuePairs);

    BooleanValue deletePayment(String paymentUuid, boolean permanent);

    BooleanValue deletePayments(DeletePaymentCommand command);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreatePaymentCommand {
        @NotBlank(message = "주류도매 아이디는 필수 입력입니다.")
        String wholesalerUuid;
        String supplierUuid;
        String supplierCode;
        String supplierName;
        String roadAddress;
        String roadAddressDetails;
        String representativeName;
        String representativeMobile;
        String representativeTelephone;
        String createDate;
        String paymentDate;
        VatType vatType;
        Integer closeBondAmount;
        Integer balanceBondAmount;
        Integer paymentAmount;
        Integer containerOffsetAmount;
        Integer totalPaymentAmount;
        String bankName;
        String bankAccount;
        String description;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class DeletePaymentCommand {
        Set<String> paymentUuids;
        boolean permanent;
    }
}
