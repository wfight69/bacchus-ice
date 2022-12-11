package com.davada.application.maintenance.persistence.data;

import com.davada.domain.common.vo.BusinessCategory;
import com.davada.domain.common.vo.YN;
import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.order.vo.CalculateStatus;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "retail_maintenance")
public class RetailMaintenanceData {
    @Id
    private String maintenanceUuid;
    private String icesalerUuid;
    //
    private String wholesalerUuid;
    private String retailShopUuid;
    private String retailShopCode;
    private String retailShopName;
    @Enumerated(EnumType.STRING)
    private BusinessCategory businessCategory;
    private String employeeUuid;
    private String warehouseCode;
    private String employeeName;
    private String employeeCode;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code",
                    column = @Column(name = "sales_course_code")),
            @AttributeOverride(name = "name",
                    column = @Column(name = "sales_course_name")),
    })
    private String warehouseUuid;
    private String warehouseName;
    @Enumerated(EnumType.STRING)
    private YN invoiceIssueYn;
    @Enumerated(EnumType.STRING)
    private YN vatYn;
    @Enumerated(EnumType.STRING)
    private CalculateStatus calculateStatus;
    
    @Enumerated(EnumType.STRING)
    private MaintenanceType maintenanceType;
    
    private String orderDate;
    private String orderTime;
    private String orderCreateDate;
    private String orderCreateTime;
    private String productShortName;
    private Integer containerQuantity;
    private Integer bottleQuantity;
    private BigDecimal amount;
    private BigDecimal vat;
    private BigDecimal subtotalAmount;
    private BigDecimal containerDeposit;
    private BigDecimal bottleDeposit;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private RetailMaintenanceStatus retailMaintenanceStatus;
    private String description;
    private YN includeRfidProductYn;
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private Set<RetailMaintenanceItemData> orderItems = new LinkedHashSet<>();
    //---------
    @Enumerated(EnumType.STRING)
    private RetailMaintenanceChannel retailMaintenanceChannel;
    @Enumerated(EnumType.STRING)
    private YN readYn;
    @Enumerated(EnumType.STRING)
    private YN registerMaintenanceYn;
    private String retailMaintenanceTelephone;
    private String orderDescription;
    private String voiceFileId;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    private void updateProductShortName() {
        this.productShortName = orderItems.stream().findFirst()
                .map(orderItem -> orderItem.getProductName() + " 외 " + orderItems.size() + "건")
                .orElse("");
    }

    public void reCalculateMaintenance() {
        containerQuantity = 0;
        bottleQuantity = 0;
        amount = BigDecimal.ZERO;
        vat = BigDecimal.ZERO;
        subtotalAmount = BigDecimal.ZERO;
        containerDeposit = BigDecimal.ZERO;
        bottleDeposit = BigDecimal.ZERO;
        totalAmount = BigDecimal.ZERO;

        orderItems.forEach(orderItem -> {
            containerQuantity = containerQuantity + orderItem.getContainerQuantity();
            bottleQuantity = bottleQuantity + orderItem.getBottleQuantity();
            amount = amount.add(orderItem.getAmount());
            vat = vat.add(orderItem.getVat());
            subtotalAmount = subtotalAmount.add(orderItem.getSubtotalAmount());
            containerDeposit = containerDeposit.add(orderItem.getContainerDeposit());
            bottleDeposit = bottleDeposit.add(orderItem.getBottleDeposit());
            totalAmount = totalAmount.add(orderItem.getTotalAmount());
            if (StringUtils.isNotBlank(orderItem.getRfidBoxTag()) && StringUtils.isNotBlank(orderItem.getRfidEaTag())) {
                this.includeRfidProductYn = YN.Y;
            }
        });
        updateProductShortName();
    }

    public void deleteMaintenanceStatus() {
        retailMaintenanceStatus = RetailMaintenanceStatus.CANCELLED;
        registerMaintenanceYn = YN.N;
    }

    public boolean updateValues(NameValuePairs nameValuePairs, RetailMaintenanceDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("retailShopUuid",
                    value -> this.retailShopUuid = trim(value));
            nameValuePairs.pullOut("retailShopCode",
                    value -> this.retailShopCode = trim(value));
            nameValuePairs.pullOut("retailShopName",
                    value -> this.retailShopName = trim(value));
            nameValuePairs.pullOut("businessCategory",
                    value -> this.businessCategory = BusinessCategory.valueOf(value));
            nameValuePairs.pullOut("employeeUuid",
                    value -> this.employeeUuid = trim(value));
            nameValuePairs.pullOut("employeeName",
                    value -> this.employeeName = trim(value));
            nameValuePairs.pullOut("employeeCode",
                    value -> this.employeeCode = trim(value));
            nameValuePairs.pullOut("salesCourse",
                    value -> this.salesCourse = jpaMapper.toCodeNameData(fromJson(value, CodeName.class)));
            nameValuePairs.pullOut("warehouseUuid",
                    value -> this.warehouseUuid = trim(value));
            nameValuePairs.pullOut("warehouseCode",
                    value -> this.warehouseCode = trim(value));
            nameValuePairs.pullOut("warehouseName",
                    value -> this.warehouseName = trim(value));
            nameValuePairs.pullOut("invoiceIssueYn",
                    value -> this.invoiceIssueYn = YN.valueOf(value));
            nameValuePairs.pullOut("vatYn",
                    value -> this.vatYn = YN.valueOf(value));
            nameValuePairs.pullOut("calculateStatus",
                    value -> this.calculateStatus = CalculateStatus.valueOf(value));
            nameValuePairs.pullOut("salesType",
                    value -> this.salesType = SalesType.valueOf(value));
            nameValuePairs.pullOut("orderCreateDate",
                    value -> this.orderCreateDate = trim(value));
            nameValuePairs.pullOut("orderCreateTime",
                    value -> this.orderCreateTime = trim(value));
            nameValuePairs.pullOut("productShortName",
                    value -> this.productShortName = trim(value));
            nameValuePairs.pullOut("containerQuantity",
                    value -> this.containerQuantity = Integer.parseInt(value));
            nameValuePairs.pullOut("bottleQuantity",
                    value -> this.bottleQuantity = Integer.parseInt(value));

            nameValuePairs.pullOut("amount",
                    value -> this.amount = new BigDecimal(value));
            nameValuePairs.pullOut("vat",
                    value -> this.vat = new BigDecimal(value));
            nameValuePairs.pullOut("subtotalAmount",
                    value -> this.subtotalAmount = new BigDecimal(value));
            nameValuePairs.pullOut("containerDeposit",
                    value -> this.containerDeposit = new BigDecimal(value));
            nameValuePairs.pullOut("bottleDeposit",
                    value -> this.bottleDeposit = new BigDecimal(value));
            nameValuePairs.pullOut("totalAmount",
                    value -> this.totalAmount = new BigDecimal(value));
//            nameValuePairs.pullOut("retailMaintenanceStatus",
//                    value -> {
//                        this.retailMaintenanceStatus = RetailMaintenanceStatus.valueOf(value);
//                        if (this.retailMaintenanceStatus == RetailMaintenanceStatus.ACCEPTED) {
//                            this.registerMaintenanceYn = YN.Y;
//                        }
//                    });
            nameValuePairs.pullOut("description",
                    value -> this.description = trim(value));
            nameValuePairs.pullOut("includeRfidProductYn",
                    value -> this.includeRfidProductYn = YN.valueOf(value));

            nameValuePairs.pullOut("retailMaintenanceChannel",
                    value -> this.retailMaintenanceChannel = RetailMaintenanceChannel.valueOf(value));
            nameValuePairs.pullOut("readYn",
                    value -> this.readYn = YN.valueOf(value));
            nameValuePairs.pullOut("registerMaintenanceYn",
                    value -> this.registerMaintenanceYn = YN.valueOf(value));
            nameValuePairs.pullOut("retailMaintenanceTelephone",
                    value -> this.retailMaintenanceTelephone = trim(value));
            nameValuePairs.pullOut("orderDescription",
                    value -> this.orderDescription = trim(value));
            nameValuePairs.pullOut("voiceFileId",
                    value -> this.voiceFileId = trim(value));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }

}
