package com.davada.domain.common;

public abstract class AuditableEntity implements Auditable, Entity {
    AuditLog auditLog = new AuditLog();

    @Override
    public AuditLog getAuditLog() {
        return auditLog;
    }

    @Override
    public void setAuditLog(AuditLog auditLog) {
        this.auditLog = auditLog;
    }
}
