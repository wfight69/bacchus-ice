package com.davada.application.code.service.port;

import com.davada.domain.code.vo.ErpCodeSetType;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.dto.code.ErpCodeSetDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ErpCodeSetCrudUseCase {
    IdValue createErpCodeSet(CreateErpCodeSetCommand command);

    ErpCodeSetDto retrieveErpCodeSet(String codeSetUuid);

    List<ErpCodeSetDto> retrieveAllErpCodeSet(String wholesalerUuid);

    BooleanValue updateErpCodeSet(String codeSetUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteErpCodeSet(String codeSetUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CreateErpCodeSetCommand {
        private String wholesalerUuid;
        private ErpCodeSetType type;
        private String name;
        private String label;
        private String description;
    }
}
