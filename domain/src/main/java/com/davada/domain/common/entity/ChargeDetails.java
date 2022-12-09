package com.davada.domain.common.entity;

import com.davada.domain.common.Refinable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 세금계산서 담당자 상세정보
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargeDetails implements Refinable {
    //부서명1,
    private String taxDepartmentName;
    //전화번호
    private String taxTelephone;
    //담당자1,
    private String taxChargeName;
    //메일
    private String taxEmail;

    @Override
    public void refineValues() {

    }
}
