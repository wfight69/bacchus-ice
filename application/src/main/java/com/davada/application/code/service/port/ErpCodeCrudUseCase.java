package com.davada.application.code.service.port;

import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.dto.code.ErpCodeDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ErpCodeCrudUseCase {
    IdValue createErpCode(CreateErpCodeCommand command);

    BooleanValue saveErpCode(SaveErpCodeCommand command);

    ErpCodeDto retrieveErpCode(String codeUuid);

    List<ErpCodeDto> retrieveAllErpCode(String wholesalerUuid);

    BooleanValue updateErpCode(String codeUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteErpCode(String codeUuid, boolean permanent);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreateErpCodeCommand {
        private String codeUuid;
        private String wholesalerUuid;
        private String codeSetName;
        private String name;
        private String label;
        private String refCode;
        private String description;
        private boolean enabled;

        private String codeSetUuid;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class SaveErpCodeCommand {
        private List<CreateErpCodeCommand> codes;
    }
}
