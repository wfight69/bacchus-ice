package com.davada.dto.wholesaler;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WholesalerDto {
    //
    String icesalerCode;
    // 업체명
    String icesalerName;
    // 업체코드
    String wholesalerCode;
    // 업체명
    String wholesalerName;
    // 사업자번호
    String businessNumber;
    // 업태
    String uptae;
    // 종목
    String jongmok;
}
