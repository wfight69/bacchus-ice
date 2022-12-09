package com.davada.application.retailshop.persistence.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 세금계산서 담당자 상세정보
 */
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargeDetailsData {
    private String taxDepartmentName;
    private String taxTelephone;
    private String taxChargeName;
    private String taxEmail;
}
