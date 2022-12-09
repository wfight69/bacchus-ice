package com.davada.domain.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuditLog {
    Long createTimestamp;
    String createUserId;
    String createIp;
    Long updateTimestamp;
    String updateUserId;
    String updateIp;
    Long deleteTimestamp;
    String deleteUserId;
    String deleteIp;
    Boolean deleted = Boolean.FALSE;

    public static AuditLog getInstance() {
        return new AuditLog();
    }

    public static AuditLog getInstance(EntityAudit createAudit) {
        AuditLog auditLog = new AuditLog();
        auditLog.createTimestamp = createAudit.getTimestamp();
        auditLog.createUserId = createAudit.getUserId();
        auditLog.createIp = createAudit.getIp();
        return auditLog;
    }
}
