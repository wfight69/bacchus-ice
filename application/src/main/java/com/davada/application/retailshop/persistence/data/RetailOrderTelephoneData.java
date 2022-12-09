package com.davada.application.retailshop.persistence.data;

import com.davada.application.retailshop.persistence.RetailOrderTelephoneDataMapper;
import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.YN;
import com.davada.domain.retailshop.entity.RetailOrderTelephone;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "retail_order_telephone",
        indexes = @Index(name = "unique_telephone", columnList = "telephone", unique = true))
public class RetailOrderTelephoneData {
    @Id
    String retailOrderTelephoneUuid;
    String retailShopUuid;
    String retailShopName;
    String telephone;
    String description;
    @Enumerated(EnumType.STRING)
    YN useYn;
    @Embedded
    AuditLogData auditLog;
    @Version
    Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, RetailOrderTelephoneDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("retailShopName",
                    value -> this.retailShopName = trim(value));
            nameValuePairs.pullOut("telephone",
                    value -> this.telephone = trim(value));
            nameValuePairs.pullOut("description",
                    value -> this.description = trim(value));
            nameValuePairs.pullOut("useYn",
                    value -> this.useYn = YN.valueOf(value));
            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }

    public boolean update(RetailOrderTelephone retailOrderTelephone) {
        this.retailShopName = retailOrderTelephone.getRetailShopName();
        this.telephone = retailOrderTelephone.getTelephone();
        this.description = retailOrderTelephone.getDescription();
        this.useYn = retailOrderTelephone.getUseYn();
        return true;
    }
}