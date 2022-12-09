package com.davada.application.common.persistence.data;

import com.davada.domain.common.EntityAudit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuditLogData {
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

    public void applyUpdateAuditLog(EntityAudit updateAudit) {
        this.updateTimestamp = updateAudit.getTimestamp();
        this.updateUserId = updateAudit.getUserId();
        this.updateIp = updateAudit.getIp();
    }

    public void applyDeleteAuditLog(EntityAudit deleteAudit) {
        this.deleteTimestamp = deleteAudit.getTimestamp();
        this.deleteUserId = deleteAudit.getUserId();
        this.deleteIp = deleteAudit.getIp();
        this.deleted = Boolean.TRUE;
    }
}
