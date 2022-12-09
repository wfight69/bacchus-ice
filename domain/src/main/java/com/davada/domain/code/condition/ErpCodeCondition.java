package com.davada.domain.code.condition;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErpCodeCondition {
    private String wholesalerUuid;
    private String codeSetUuid;
    private String codeSetName;
    private String name;
    private String nameKeyword;
    private String label;
    private String refCode;
    private Boolean enabled;
}
