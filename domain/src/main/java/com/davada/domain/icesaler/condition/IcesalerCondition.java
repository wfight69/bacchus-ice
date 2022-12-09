package com.davada.domain.icesaler.condition;

import com.davada.domain.common.vo.IndustryType;
import com.davada.domain.wholesaler.vo.Province;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IcesalerCondition {
    // 업체코드
    String icesalerCode;
    // 업종 유형(주류, 제조사, 냉동_냉장)
    IndustryType industryType;
    // 지역
    Province province;
    // 업체명
    String icesalerName;
    // 사업자등록번호
    String businessNumber;
}
