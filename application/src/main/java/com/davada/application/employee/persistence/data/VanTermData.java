package com.davada.application.employee.persistence.data;

import com.davada.domain.common.vo.YN;
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
public class VanTermData {

    // 판매담당자 단말기 SMS전송여부
    @Enumerated(EnumType.STRING)
    private YN smsSendYn;               //  주문서비스 전송여부(Y/N : 단말기->서버)
    @Enumerated(EnumType.STRING)
    private YN filterUseYn;             // 필터사용여부(Y/N)

    // 판매담당자 단말기 Fcm위한 Device토큰
    private String fcmDeviceToken;

    // 판매담당자 단말정보 설정
    private String vanDeviceSerialNo;        // 단말기 제조번호(ksnet등록 단말기번호)
    private String vanTermNo;                // 단말기번호(ksnet 정보다운성공시)
    private String vanTermCertKey;            // 단말기인증키(ksnet등록 정보다운성공시)
    private String vanTermSerialNo;        // 단말기시리얼번호(ksnet등록 정보다운성공시)
    private String vanTermNtsCertKey;
}
