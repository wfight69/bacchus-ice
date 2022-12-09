package com.davada.dto.order;

import com.davada.domain.order.vo.RetailBeverageContainer;
import com.davada.domain.product.vo.UnitPrice;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailOrderItemDto {
    String orderItemUuid;
    String productUuid;
    String productName;
    String productCode;
    String volume;
    Integer bottlesInBox;
    RetailBeverageContainer beverageContainer;
    Boolean isContractPrice;
    BigDecimal profitMarginRate;
    UnitPrice containerPrice;
    Integer containerQuantity;
    Integer bottleQuantity;
    BigDecimal amount;
    BigDecimal vat;
    BigDecimal subtotalAmount;
    BigDecimal containerDeposit;
    BigDecimal bottleDeposit;
    BigDecimal totalAmount;
}
