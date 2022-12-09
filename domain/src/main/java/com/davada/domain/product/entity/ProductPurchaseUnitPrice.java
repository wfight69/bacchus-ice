package com.davada.domain.product.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.product.vo.UnitPrice;
import lombok.*;

import java.math.BigDecimal;

/**
 * 상품 구매 단가
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPurchaseUnitPrice extends AuditableEntity  implements Refinable {
    //판매단가 UUID
    String purchaseUnitPriceUuid;
    //품목 UUID
    String productUuid;

    //상자: 소계금액, 공급가, 부가세
    UnitPrice containerPrice;
    //본수: 소계금액, 공급가, 부가세
    UnitPrice bottlePrice;
    //공급합계
    BigDecimal totalPrice;

    //단가적용시기
    String applyStartDate;
    //단가적용종기
    String applyEndDate;

    @Override
    public void refineValues() {

    }
}