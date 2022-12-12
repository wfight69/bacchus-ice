package com.davada.application.maintenance.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.maintenance.persistence.RetailMaintenanceDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.YN;
import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.maintenance.vo.RetailMaintenanceChannel;
import com.davada.domain.maintenance.vo.RetailMaintenanceStatus;
import com.davada.domain.order.vo.CalculateStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.davada.domain.common.util.StringHelper.trim;

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
    private String wholesalerName;
    private String wholesalerCeoTelephone;
    private String retailShopUuid;
    private String retailShopCode;
    private String retailShopName;

    // 등록관련-----------------------
    private String createDate;
    private String createTime;
    // 유지관리 등록내역
    private String createDescription;

    // 품목명외
    private String productShortName;

    // 접수관련-----------------------
    private String acceptDate;
    private String acceptTime;
    private String employeeUuid;
    private String employeeCode;
    private String employeeName;
    private String acceptDescription;

    // 유지관리(a/s)진행관련-----------------------
    private String maintenanceDate;
    private String maintenanceTime;
    private String maintenanceEmployeeUuid;
    private String maintenanceEmployeeCode;
    private String maintenanceEmployeeName;
    private String maintenanceDescription;

    // 유지관리(a/s)승인관련-----------------------
    private String approvalDate;
    private String approvalTime;
    private String approvalEmployeeUuid;
    private String approvalEmployeeCode;
    private String approvalEmployeeName;
    private String approvalDescription;

    // 마감여부 미마감/마감
    @Enumerated(EnumType.STRING)
    private CalculateStatus calculateStatus;

    // 유지관리유형 출고(판매)/수리/회수/정비/기타
    @Enumerated(EnumType.STRING)
    private MaintenanceType maintenanceType;

    // 유지관리 상태
    @Enumerated(EnumType.STRING)
    private RetailMaintenanceStatus retailMaintenanceStatus;

    // 유지관리 체널 전화/주류도매/소매점 앱
    @Enumerated(EnumType.STRING)
    private RetailMaintenanceChannel retailMaintenanceChannel;

    private String warehouseUuid;
    private String warehouseCode;
    private String warehouseName;

    private Integer quantity;
    private BigDecimal amount;
    private BigDecimal vat;
    private BigDecimal subtotalAmount;
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "maintenance", orphanRemoval = true)
    private Set<RetailMaintenanceItemData> maintenanceItems = new LinkedHashSet<>();
    //---------
    @Enumerated(EnumType.STRING)
    private YN invoiceIssueYn;

    private String description;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    private void updateProductShortName() {
        this.productShortName = maintenanceItems.stream().findFirst()
                .map(orderItem -> orderItem.getProductName() + " 외 " + maintenanceItems.size() + "건")
                .orElse("");
    }

    public void reCalculateMaintenance() {
        quantity = 0;
        amount = BigDecimal.ZERO;
        vat = BigDecimal.ZERO;
        subtotalAmount = BigDecimal.ZERO;
        totalAmount = BigDecimal.ZERO;

        maintenanceItems.forEach(orderItem -> {
            quantity = quantity + orderItem.getQuantity();
            amount = amount.add(orderItem.getAmount());
            vat = vat.add(orderItem.getVat());
            subtotalAmount = subtotalAmount.add(orderItem.getSubtotalAmount());
            totalAmount = totalAmount.add(orderItem.getTotalAmount());
        });
        updateProductShortName();
    }

    public void deleteMaintenanceStatus() {
        retailMaintenanceStatus = RetailMaintenanceStatus.CANCELLED;
        //registerMaintenanceYn = YN.N;
    }

    public boolean updateValues(NameValuePairs nameValuePairs, RetailMaintenanceDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("icesalerUuid",
                    value -> this.icesalerUuid = trim(value));
            //
            nameValuePairs.pullOut("warehouseUuid",
                    value -> this.warehouseUuid = trim(value));
            nameValuePairs.pullOut("wholesalerName",
                    value -> this.wholesalerName = trim(value));
            nameValuePairs.pullOut("warehouseUuid",
                    value -> this.warehouseUuid = trim(value));
            //
            nameValuePairs.pullOut("retailShopUuid",
                    value -> this.retailShopUuid = trim(value));
            nameValuePairs.pullOut("retailShopCode",
                    value -> this.retailShopCode = trim(value));
            nameValuePairs.pullOut("retailShopName",
                    value -> this.retailShopName = trim(value));
            //
            nameValuePairs.pullOut("createDate",
                    value -> this.createDate = trim(value));
            nameValuePairs.pullOut("createTime",
                    value -> this.createTime = trim(value));
            nameValuePairs.pullOut("createDescription",
                    value -> this.createDescription = trim(value));
            //
            nameValuePairs.pullOut("productShortName",
                    value -> this.productShortName = trim(value));
            //
            nameValuePairs.pullOut("acceptDate",
                    value -> this.acceptDate = trim(value));
            nameValuePairs.pullOut("acceptTime",
                    value -> this.acceptTime = trim(value));
            nameValuePairs.pullOut("employeeUuid",
                    value -> this.employeeUuid = trim(value));
            nameValuePairs.pullOut("employeeCode",
                    value -> this.employeeCode = trim(value));
            nameValuePairs.pullOut("employeeName",
                    value -> this.employeeName = trim(value));
            nameValuePairs.pullOut("acceptDescription",
                    value -> this.acceptDescription = trim(value));
            //
            nameValuePairs.pullOut("maintenanceDate",
                    value -> this.maintenanceDate = trim(value));
            nameValuePairs.pullOut("maintenanceTime",
                    value -> this.maintenanceTime = trim(value));
            nameValuePairs.pullOut("maintenanceEmployeeUuid",
                    value -> this.maintenanceEmployeeUuid = trim(value));
            nameValuePairs.pullOut("maintenanceEmployeeCode",
                    value -> this.maintenanceEmployeeCode = trim(value));
            nameValuePairs.pullOut("maintenanceEmployeeName",
                    value -> this.maintenanceEmployeeName = trim(value));
            nameValuePairs.pullOut("maintenanceDescription",
                    value -> this.maintenanceDescription = trim(value));
            //
            nameValuePairs.pullOut("approvalDate",
                    value -> this.approvalDate = trim(value));
            nameValuePairs.pullOut("maintenanceTime",
                    value -> this.approvalTime = trim(value));
            nameValuePairs.pullOut("approvalEmployeeUuid",
                    value -> this.approvalEmployeeUuid = trim(value));
            nameValuePairs.pullOut("approvalEmployeeCode",
                    value -> this.approvalEmployeeCode = trim(value));
            nameValuePairs.pullOut("approvalEmployeeName",
                    value -> this.approvalEmployeeName = trim(value));
            nameValuePairs.pullOut("approvalDescription",
                    value -> this.approvalDescription = trim(value));
            //
            nameValuePairs.pullOut("warehouseUuid",
                    value -> this.warehouseUuid = trim(value));
            nameValuePairs.pullOut("warehouseCode",
                    value -> this.warehouseCode = trim(value));
            nameValuePairs.pullOut("warehouseName",
                    value -> this.warehouseName = trim(value));
            //
            nameValuePairs.pullOut("calculateStatus",
                    value -> this.calculateStatus = CalculateStatus.valueOf(value));
            nameValuePairs.pullOut("maintenanceType",
                    value -> this.maintenanceType = maintenanceType.valueOf(value));
            nameValuePairs.pullOut("retailMaintenanceChannel",
                    value -> this.retailMaintenanceStatus = retailMaintenanceStatus.valueOf(value));
            nameValuePairs.pullOut("retailMaintenanceChannel",
                    value -> this.retailMaintenanceChannel = RetailMaintenanceChannel.valueOf(value));
            //
            nameValuePairs.pullOut("quantity",
                    value -> this.quantity = Integer.parseInt(value));
            nameValuePairs.pullOut("amount",
                    value -> this.amount = new BigDecimal(value));
            nameValuePairs.pullOut("vat",
                    value -> this.vat = new BigDecimal(value));
            nameValuePairs.pullOut("subtotalAmount",
                    value -> this.subtotalAmount = new BigDecimal(value));
            nameValuePairs.pullOut("totalAmount",
                    value -> this.totalAmount = new BigDecimal(value));
            //
            nameValuePairs.pullOut("invoiceIssueYn",
                    value -> this.invoiceIssueYn = YN.valueOf(value));
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
