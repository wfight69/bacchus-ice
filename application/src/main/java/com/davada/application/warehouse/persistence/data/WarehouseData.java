package com.davada.application.warehouse.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.common.persistence.data.LocationData;
import com.davada.application.warehouse.persistence.WarehouseDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.product.vo.WarehouseType;
import com.davada.domain.wholesaler.vo.Location;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Entity
@Table(name = "warehouse")
public class WarehouseData {
    @Id
    private String warehouseUuid;
    private String wholesalerUuid;
    private String warehouseCode;
    private String warehouseName;
    @Embedded
    private LocationData location;
    private String telephone;
    private String employeeUuid;
    private String employeeName;
    private String supplierUuid;
    private String supplierName;
    @Enumerated(EnumType.STRING)
    private WarehouseType warehouseType;
    private String description;
    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, WarehouseDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("warehouseCode",
                    value -> this.warehouseCode = trim(value));
            nameValuePairs.pullOut("warehouseName",
                    value -> this.warehouseName = trim(value));
            nameValuePairs.pullOut("location",
                    value -> this.location = jpaMapper.toLocationData(fromJson(value, Location.class)));
            nameValuePairs.pullOut("telephone",
                    value -> this.telephone = trim(value));
            nameValuePairs.pullOut("employeeUuid",
                    value -> this.employeeUuid = trim(value));
            nameValuePairs.pullOut("employeeName",
                    value -> this.employeeName = trim(value));
            nameValuePairs.pullOut("supplierUuid",
                    value -> this.supplierUuid = trim(value));
            nameValuePairs.pullOut("supplierName",
                    value -> this.supplierName = trim(value));
            nameValuePairs.pullOut("warehouseType",
                    value -> this.warehouseType = WarehouseType.valueOf(value));
            nameValuePairs.pullOut("description",
                    value -> this.description = trim(value));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
