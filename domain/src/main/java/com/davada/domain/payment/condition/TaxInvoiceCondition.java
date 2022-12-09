package com.davada.domain.payment.condition;

import com.davada.domain.common.vo.YN;
import com.davada.domain.payment.vo.IssueTaxStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaxInvoiceCondition {
    String wholesalerUuid;
    String retailShopUuid;
    String retailShopCode;
    String retailShopName;
    YN issueTaxYn;
    IssueTaxStatus issueTaxStatus;
    String startWriteDate;
    String endWriteDate;
    String startCreateDate;
    String endCreateDate;
}
