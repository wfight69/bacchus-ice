package com.davada.domain.code.condition;

import com.davada.domain.code.vo.ErpCodeSetType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErpCodeSetCondition {
    private String wholesalerUuid;
    private String name;
    private String nameKeyword;
    private String label;
    private ErpCodeSetType erpCodeSetType;
}
