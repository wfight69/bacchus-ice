package com.davada.application.payment.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.common.persistence.data.CodeNameData;
import com.davada.application.payment.persistence.DepositDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.VatType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "deposit")
public class DepositData {
    @Id
    private String depositUuid;
    private String wholesalerUuid;
    private String retailShopUuid;
    private String retailShopCode;
    private String retailShopName;
    private String roadAddress;
    private String roadAddressDetails;
    private String representativeName;
    private String representativeMobile;
    private String representativeTelephone;
    private String employeeUuid;
    private String employeeCode;
    private String employeeName;
    private String createDate;
    private String paymentDate;
    private VatType vatType;
    private Integer balanceAmount;
    private String bankName;
    private String bankAccount;
    private CodeNameData accountSubject;
    private Integer closeBondAmount;
    private Integer balanceBondAmount;
    private Integer cardPaymentAmount;
    private Integer cashPaymentAmount;
    private Integer accountPaymentAmount;
    private Integer offsettingAmount;
    private Integer totalPaymentAmount;
    private YN closeYn;
    private String description;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public void reCalculateOrder() {
    }

    public boolean updateValues(NameValuePairs nameValuePairs, DepositDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("retailShopUuid",
                    value -> this.retailShopUuid = trim(value));
            nameValuePairs.pullOut("retailShopCode",
                    value -> this.retailShopCode = trim(value));
            nameValuePairs.pullOut("retailShopName",
                    value -> this.retailShopName = trim(value));
            nameValuePairs.pullOut("roadAddress",
                    value -> this.roadAddress = trim(value));
            nameValuePairs.pullOut("roadAddressDetails",
                    value -> this.roadAddressDetails = trim(value));
            nameValuePairs.pullOut("representativeName",
                    value -> this.representativeName = trim(value));
            nameValuePairs.pullOut("representativeMobile",
                    value -> this.representativeMobile = trim(value));
            nameValuePairs.pullOut("representativeTelephone",
                    value -> this.representativeTelephone = trim(value));
            nameValuePairs.pullOut("employeeUuid",
                    value -> this.employeeUuid = trim(value));
            nameValuePairs.pullOut("employeeCode",
                    value -> this.employeeCode = trim(value));
            nameValuePairs.pullOut("employeeName",
                    value -> this.employeeName = trim(value));
            nameValuePairs.pullOut("vatType",
                    value -> this.vatType = VatType.valueOf(value));
            nameValuePairs.pullOut("createDate",
                    value -> this.createDate = trim(value));
            nameValuePairs.pullOut("paymentDate",
                    value -> this.paymentDate = trim(value));
            nameValuePairs.pullOut("balanceAmount",
                    value -> this.balanceAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("bankName",
                    value -> this.bankName = trim(value));
            nameValuePairs.pullOut("bankAccount",
                    value -> this.bankAccount = trim(value));
//            nameValuePairs.pullOut("accountSubject",
//                    value -> this.accountSubject = jpaMapper.toCodeNameData(fromJson(value, CodeName.class)));
            nameValuePairs.pullOut("closeBondAmount",
                    value -> this.closeBondAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("balanceBondAmount",
                    value -> this.balanceBondAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("cardPaymentAmount",
                    value -> this.cardPaymentAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("cashPaymentAmount",
                    value -> this.cashPaymentAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("accountPaymentAmount",
                    value -> this.accountPaymentAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("offsettingAmount",
                    value -> this.offsettingAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("totalPaymentAmount",
                    value -> this.totalPaymentAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("closeYn",
                    value -> this.closeYn = YN.valueOf(value));
            nameValuePairs.pullOut("description",
                    value -> this.description = value);

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
