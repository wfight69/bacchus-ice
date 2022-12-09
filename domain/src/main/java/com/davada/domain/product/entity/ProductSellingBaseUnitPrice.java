package com.davada.domain.product.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.product.vo.UnitPrice;
import lombok.*;

import java.math.BigDecimal;

/**
 * 상품 판매 기준단가
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSellingBaseUnitPrice extends AuditableEntity  implements Refinable {
    //판매단가 UUID
    String sellingUnitPriceUuid;
    //품목 UUID
    String productUuid;

    //*일반(마진율)
    BigDecimal generalProfitMarginRate;
    //*일반(상자): 소계금액, 공급가, 부가세
    UnitPrice generalContainerPrice;
    //*일반(본수): 소계금액, 공급가, 부가세
    UnitPrice generalBottlePrice;

    //*유흥(마진율)
    BigDecimal entProfitMarginRate;
    //*유흥(상자): 소계금액, 공급가, 부가세
    UnitPrice entContainerPrice;
    //*유흥(본수): 소계금액, 공급가, 부가세
    UnitPrice entBottlePrice;

    @Override
    public void refineValues() {

    }
}
