package com.davada.domain.code.entity;

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
public class ErpCode extends AuditableEntity implements Refinable {
    @EntityId
    private String codeUuid;
    private String wholesalerUuid;
    private String codeSetName;
    private String name;
    private String label;
    private String refCode;
    private String description;
    private boolean enabled;

    private String codeSetUuid;

    @Override
    public void refineValues() {

    }
}
