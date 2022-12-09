package com.davada.domain.retailshop.vo;

import com.davada.domain.common.Refinable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServicePeriod implements Refinable {
    // 거래 시작일자
    private String serviceStartDate;
    // 거래 종료일자
    private String serviceEndDate;

    @Override
    public void refineValues() {

    }
}