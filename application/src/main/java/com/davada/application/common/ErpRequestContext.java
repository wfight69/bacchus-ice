package com.davada.application.common;

import com.davada.domain.common.EntityAudit;

public interface ErpRequestContext {
    //    User getCurrentUser();
    EntityAudit getEntityAudit();
}
