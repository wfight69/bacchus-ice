package com.davada.application.code.persistence.data;

import com.davada.application.code.persistence.ErpCodeSetDataMapper;
import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.domain.code.vo.ErpCodeSetType;
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
@Table(name = "erp_code_set")
public class ErpCodeSetData {
    @Id
    private String codeSetUuid;
    private String wholesalerUuid;
    private ErpCodeSetType type;
    private String name;
    private String label;
    private String description;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, ErpCodeSetDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("label", value -> this.label = value);
            nameValuePairs.pullOut("description", value -> this.description = value);

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
