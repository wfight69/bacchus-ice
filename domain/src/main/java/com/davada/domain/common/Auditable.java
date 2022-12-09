package com.davada.domain.common;

public interface Auditable {
    AuditLog getAuditLog();
    void setAuditLog(AuditLog auditLog);
}
