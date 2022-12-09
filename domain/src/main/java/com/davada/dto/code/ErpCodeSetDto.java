package com.davada.dto.code;

import com.davada.domain.code.vo.ErpCodeSetType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErpCodeSetDto {
    private String codeSetUuid;
    private String wholesalerUuid;
    private ErpCodeSetType type;
    private String name;
    private String label;
    private String description;
}