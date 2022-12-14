package com.davada.application.payment.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.payment.vo.VatType;

import com.davada.dto.payment.DepositDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

public interface DepositCrudUseCase {

    IdValue createDeposit(CreateDepositCommand command);

    DepositDto retrieveDeposit(String depositUuid);

    List<DepositDto> retrieveAllDeposit(String wholesalerUuid);

    BooleanValue updateDeposit(String depositUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteDeposit(String depositUuid, boolean permanent);

    BooleanValue deleteDeposits(DeleteDepositCommand command);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreateDepositCommand {
        @NotBlank(message = "주류도매 아이디는 필수 입력입니다.")
        String wholesalerUuid;
        String retailShopUuid;
        String retailShopCode;
        String retailShopName;
        String roadAddress;
        String roadAddressDetails;
        String representativeName;
        String representativeMobile;
        String representativeTelephone;
        String employeeUuid;
        String employeeCode;
        String employeeName;
        String createDate;
        String paymentDate;
        VatType vatType;
        Integer balanceAmount;
        String bankName;
        String bankAccount;
        CodeName accountSubject;
        Integer closeBondAmount;
        Integer balanceBondAmount;

        Integer cardPaymentAmount;
        Integer cashPaymentAmount;
        Integer accountPaymentAmount;
        Integer offsettingAmount;
        Integer totalPaymentAmount;
        String description;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class DeleteDepositCommand {
        Set<String> depositUuids;
        boolean permanent;
    }
}
