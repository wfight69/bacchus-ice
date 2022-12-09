package com.davada.domain.supplier.condition;

import com.davada.domain.common.vo.YN;
import com.davada.domain.wholesaler.vo.Province;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupplierCondition {
    String wholesalerUuid;
    Province province;
    YN substitutionYn;
    String supplierCode;
    String supplierName;
}