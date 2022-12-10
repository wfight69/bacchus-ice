package com.davada.application.code.persistence.data;

import com.davada.application.code.persistence.ErpCodeDataMapper;
import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Entity
@Table(name = "erp_code")
public class ErpCodeData {
    @Id
    private String codeUuid;
    private String wholesalerUuid;
    private String codeSetName;
    private String name;
    private String label;
    private String refCode;
    private String description;
    private boolean enabled;

    private String codeSetUuid;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, ErpCodeDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("label", value -> this.label = value);
            nameValuePairs.pullOut("refCode", value -> this.refCode = value);
            nameValuePairs.pullOut("description", value -> this.description = value);
            nameValuePairs.pullOut("enabled", value -> this.enabled = Boolean.parseBoolean(value));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
