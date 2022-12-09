package com.davada.application.wholesaler.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.*;
import com.davada.domain.wholesaler.entity.Wholesaler;
import com.davada.domain.wholesaler.vo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface WholesalerCrudUseCase {

    IdValue createWholesaler(Wholesaler wholesaler);

    Wholesaler retrieveWholesaler(String wholesalerUuid);

    BooleanValue updateWholesaler(String wholesalerUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteWholesaler(String wholesalerUuid, boolean permanent);

    List<Wholesaler> retrieveAllWholesaler();

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreateWholesalerCommand {
        String icesalerUuid;
        @NotBlank(message = "주류도매코드는 필수 입력입니다.")
        String wholesalerCode;
        String wholesalerName;
        Location location;
        String businessNumber;
        String corporationNumber;
        String uptae;
        String jongmok;
        CompanyType companyType;
        IndustryType industryType;
        Contact companyContact;
        Contact ceoContact;
        Contact managerContact;
        String serviceStartDate;
        Integer serviceMonthlyAmount;
        WholesalerStatus wholesalerStatus;
        String remarks;
        Van van;
        Ars ars;
        Mobile mobile;
    }
}
