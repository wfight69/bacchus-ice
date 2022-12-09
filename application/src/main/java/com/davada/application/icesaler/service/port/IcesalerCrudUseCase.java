package com.davada.application.icesaler.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.icesaler.entity.Icesaler;

import java.util.List;

public interface IcesalerCrudUseCase {

    IdValue createIcesaler(Icesaler icesaler);

    Icesaler retrieveIcesaler(String icesalerUuid);

    BooleanValue updateIcesaler(String icesalerUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteIcesaler(String icesalerUuid, boolean permanent);

    List<Icesaler> retrieveAllIcesaler();

//    @Getter
//    @AllArgsConstructor
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    class CreateIcesalerCommand {
//        @NotBlank(message = "주류도매코드는 필수 입력입니다.")
//        String icesalerCode;
//        String icesalerName;
//        Location location;
//        String businessNumber;
//        String corporationNumber;
//        String uptae;
//        String jongmok;
//        CompanyType companyType;
//        IndustryType industryType;
//        Contact contact;
//        Contact reprsContact;
//        Contact chargeContact;
//        String serviceStartDate;
//        Integer serviceMonthlyAmount;
//        IcesalerStatus icesalerStatus;
//        String remarks;
//        Van van;
//        Ars ars;
//        Mobile mobile;
//    }
}
