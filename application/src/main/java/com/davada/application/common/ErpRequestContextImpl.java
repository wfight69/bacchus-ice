package com.davada.application.common;

import com.davada.domain.common.EntityAudit;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ErpRequestContextImpl implements ErpRequestContext {

    EntityAudit entityAudit;

    @Override
    public EntityAudit getEntityAudit() {
        return entityAudit;
    }

    public void setEntityAudit(EntityAudit entityAudit) {
        this.entityAudit = entityAudit;
    }
}