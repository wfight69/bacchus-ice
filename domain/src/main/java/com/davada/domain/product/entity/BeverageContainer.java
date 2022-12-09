package com.davada.domain.product.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.product.vo.BeverageContainerType;
import com.davada.domain.product.vo.BrewingType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 용기공병
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeverageContainer extends AuditableEntity implements Refinable {

    // 용기공병 UUID
    String beverageContainerUuid;
    //주류도매업체 UUID
    String wholesalerUuid;
    // 주류제조사 UUID
    String supplierUuid;
    // 용기공병 코드
    String beverageContainerCode;
    // 용기공병 구분
    BeverageContainerType beverageContainerType;
    // 주종구분
    BrewingType brewingType;
    // 용기공병명
    String beverageContainerName;
    // 용량
    String volume;
    // 단위
    String measurement;
    // 본수
    Integer bottlesInBox;
    // 기타(비고)
    String description;
    // 용공재고
    Integer stockQuantity;
    // 용기 보증금     :
    //  매입보증금
    //  매출보증금
    //  매입수수료
    //  반납수수료(용차)
    //  반납수수료(자차)
    BeverageContainerUnitPrice containerDeposit;
    // 공병 보증금     :
    //  매입보증금
    //  매출보증금
    //  매입수수료
    //  반납수수료(용차)
    //  반납수수료(자차)
    BeverageContainerUnitPrice bottleDeposit;

    @Override
    public void refineValues() {

    }

}
