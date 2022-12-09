package com.davada.domain.retailshop.entity;


import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.YN;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 소매점의 주문 전화번호
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailOrderTelephone extends AuditableEntity implements Refinable {
    //소매점의 주문 전화번호 UUID
    String retailOrderTelephoneUuid;
    //소매점 UUID
    String retailShopUuid;
    //소매점명
    String retailShopName;
    //전화번호
    String telephone;
    //비고
    String description;
    //사용여부
    YN useYn;

    @Override
    public void refineValues() {

    }
}
