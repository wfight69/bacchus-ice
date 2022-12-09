package com.davada.application.retailshop.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.common.vo.YN;
import com.davada.domain.retailshop.entity.RetailOrderTelephone;
import com.davada.dto.retailshop.RetailOrderTelephoneDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

public interface RetailOrderTelephoneUseCase {
    // CRUD list
    IdValue createRetailOrderTelephone(CreateRetailOrderTelephoneCommand command);

    RetailOrderTelephone retrieveRetailOrderTelephone(String retailOrderTelephoneUuid);

    List<RetailOrderTelephone> retrieveAllRetailOrderTelephone(String retailShopUuid);

    BooleanValue updateRetailOrderTelephone(String retailOrderTelephoneUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteRetailOrderTelephone(String retailOrderTelephoneUuid, boolean permanent);

    BooleanValue mergeRetailOrderTelephone(String retailShopUuid, MergeRetailOrderTelephoneCommand mergeRetailOrderTelephoneCommand);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateRetailOrderTelephoneCommand {
        String retailShopUuid;
        String retailShopName;
        String telephone;
        String description;
        YN useYn;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class MergeRetailOrderTelephoneCommand {
        List<RetailOrderTelephoneDto> retailOrderTelephones = Collections.emptyList();
    }
}
