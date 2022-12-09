package com.davada.application.wholesaler.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.wholesaler.entity.Wholesaler;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface WholesalerCrudUseCase {

    IdValue createWholesaler(Wholesaler command);

    Wholesaler retrieveWholesaler(String wholesalerUuid);

    BooleanValue updateWholesaler(String wholesalerUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteWholesaler(String wholesalerUuid, boolean permanent);

    List<Wholesaler> retrieveAllWholesaler();

//    @Getter
//    @AllArgsConstructor
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    class CreateWholesalerCommand {
//        @NotBlank(message = "주류도매코드는 필수 입력입니다.")
//        String wholesalerCode;
//        String wholesalerName;
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
//        WholesalerStatus wholesalerStatus;
//        String remarks;
//        Van van;
//        Ars ars;
//        Mobile mobile;
//    }
}
