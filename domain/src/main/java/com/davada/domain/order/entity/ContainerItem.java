package com.davada.domain.order.entity;

import com.davada.domain.common.Entity;
import lombok.*;

import java.math.BigDecimal;

/**
 * 용기공병 회수/ 품목
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContainerItem implements Entity {
    // 용기공병 품목 UUID
    String containerItemUuid;
    // 용기공병 UUID
    String beverageContainerUuid;
    // 용공 코드
    String beverageContainerCode;
    // 용공 이름
    String beverageContainerName;
    // 용량
    String volume;
    // 용공 본수 30
    Integer bottlesInBox;

    // 입상자 개수 2
    Integer containerBottleQuantity;
    // 용기 개수 0
    Integer containerQuantity;
    // 공병 개수 0
    Integer bottleQuantity;

    // [단가]
    // 용기 보증금 단가 2000
    BigDecimal containerUnitDeposit;
    // 용기 수수료 단가 20
    BigDecimal containerUnitFee;
    // 공병 보증금 단가 100
    BigDecimal bottleUnitDeposit;
    // 공병 수수료 단가 19
    BigDecimal bottleUnitFee;

    // [계]
    // 용기 보증금 4000
    BigDecimal containerDeposit;
    // 용기 수수료 40
    BigDecimal containerFee;
    // 용기 수수료 부가가치세 4
    BigDecimal containerFeeVat;
    // 용기 소계(용기보증금 + 용기수수료 + 용기수수료 부가가치세) 4044
    BigDecimal containerSubtotal;

    // 공병 보증금 x 용공본수 x 개수 6000
    BigDecimal bottlesDeposit;
    // 공병 수수료 x 용공본수 x 개수 1140
    BigDecimal bottlesFee;
    //   공병 수수료 부가가치세 114
    BigDecimal bottlesFeeVat;
    // 공병 소계(공병보증금 x 용공본수 + 공병수수료 x 용공본수 + 공병 수수료 부가가치세) 7254
    BigDecimal bottlesSubtotal;

    // 합계(용기소계 + 공병소계) 11298
    BigDecimal sumTotal;
}
