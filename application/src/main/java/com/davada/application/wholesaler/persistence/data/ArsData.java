package com.davada.application.wholesaler.persistence.data;

import com.davada.domain.common.vo.YN;
import com.davada.domain.wholesaler.vo.ArsType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArsData {
    private String arsMainTelephone;    // ars대표전화번호
    @Enumerated(EnumType.STRING)
    private ArsType arsCompany;         // ars업체정보(ex. LG, SAMSUNG(삼성))
    private String arsId;
    private String password;
    private String callchngStartDate;   // 착신전환 시작일시
    private String callchngEndDate;     // 착신전환 종료일시
    @Enumerated(EnumType.STRING)
    YN             arsMentUse;          // ars멘트서비스 사용여부
    // Ars070대표전화/패스워드/일반대표전화
    private String ars1_070Telephone;
    private String ars1_070TelephonePassword;
    private String ars1_Telephone;
}
