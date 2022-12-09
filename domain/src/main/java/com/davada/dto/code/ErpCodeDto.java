package com.davada.dto.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErpCodeDto {
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
