package com.davada.application.payment.persistence.data;

import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.application.payment.persistence.PaymentDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.VatType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment")
public class PaymentData {
    @Id
    private String paymentUuid;
    private String wholesalerUuid;
    private String supplierUuid;
    private String supplierCode;
    private String supplierName;
    private String roadAddress;
    private String roadAddressDetails;
    private String representativeName;
    private String representativeMobile;
    private String representativeTelephone;
    private String createDate;
    private String paymentDate;
    private VatType vatType;
    private Integer closeBondAmount;
    private Integer balanceBondAmount;
    private Integer paymentAmount;
    private Integer containerOffsetAmount;
    private Integer totalPaymentAmount;
    private String bankName;
    private String bankAccount;
    private YN closeYn;
    private String description;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public void reCalculateOrder() {
    }

    public boolean updateValues(NameValuePairs nameValuePairs, PaymentDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {

            nameValuePairs.pullOut("supplierUuid",
                    value -> this.supplierUuid = trim(value));
            nameValuePairs.pullOut("supplierCode",
                    value -> this.supplierCode = trim(value));
            nameValuePairs.pullOut("supplierName",
                    value -> this.supplierName = trim(value));
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
            nameValuePairs.pullOut("vatType",
                    value -> this.vatType = VatType.valueOf(value));
            nameValuePairs.pullOut("createDate",
                    value -> this.createDate = trim(value));
            nameValuePairs.pullOut("paymentDate",
                    value -> this.paymentDate = trim(value));
            nameValuePairs.pullOut("balanceBondAmount",
                    value -> this.balanceBondAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("closeBondAmount",
                    value -> this.closeBondAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("balanceBondAmount",
                    value -> this.balanceBondAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("paymentAmount",
                    value -> this.paymentAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("containerOffsetAmount",
                    value -> this.containerOffsetAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("totalPaymentAmount",
                    value -> this.totalPaymentAmount = Integer.valueOf(value));
            nameValuePairs.pullOut("bankName",
                    value -> this.bankName = trim(value));
            nameValuePairs.pullOut("bankAccount",
                    value -> this.bankAccount = trim(value));
            nameValuePairs.pullOut("closeYn",
                    value -> this.closeYn = YN.valueOf(value));
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
