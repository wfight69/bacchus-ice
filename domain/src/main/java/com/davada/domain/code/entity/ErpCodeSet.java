package com.davada.domain.code.entity;

import com.davada.domain.code.vo.ErpCodeSetType;
import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.entity.EntityId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErpCodeSet extends AuditableEntity implements Refinable {
    @EntityId
    private String codeSetUuid;
    private String wholesalerUuid;
    private ErpCodeSetType type;
    private String name;
    private String label;
    private String description;

    @Override
    public void refineValues() {

    }
}
