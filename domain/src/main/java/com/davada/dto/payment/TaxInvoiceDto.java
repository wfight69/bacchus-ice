package com.davada.dto.payment;

import com.davada.domain.common.AuditLog;
import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.entity.TaxInvoiceParty;
import com.davada.domain.payment.entity.TaxInvoiceTradeLineItem;
import com.davada.domain.payment.vo.IssueTaxStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaxInvoiceDto {
    String taxInvoiceUuid;
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    TaxInvoiceParty invoicerParty;
    TaxInvoiceParty invoiceeParty;
    TaxInvoiceParty brokerParty;
    int issueDirection;
    int taxInvoiceType;
    int taxType;
    //    int taxCalcType;
    int purposeType;
    String modifyCode;
    String writeDate;
    String amountTotal;
    String taxTotal;
    String totalAmount;
    String cash;
    String chkBill;
    String note;
    String credit;
    String remark1;
    String remark2;
    String remark3;
    String kwon;
    String ho;
    String serialNum;
    List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItems;

    YN issueTaxYn;
    IssueTaxStatus issueTaxStatus;
    Integer issueResult;
    String issueErrorMessage;
    String createDate;
    AuditLog auditLog;
}
