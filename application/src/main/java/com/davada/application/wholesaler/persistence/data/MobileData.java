package com.davada.application.wholesaler.persistence.data;

import com.davada.domain.common.vo.YN;
import com.davada.domain.wholesaler.vo.OrderType;
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
public class MobileData {
    @Enumerated(EnumType.STRING)
    private OrderType orderType;            // 주문서비스타입(ex. ALL(전체), ARS(음성), SMS(sms,ktalk), MOBILE(소매점))
    //
    @Enumerated(EnumType.STRING)
    private YN smsSendYn;                 // 주문서비스 전송여부(Y/N : 단말기->서버)
    @Enumerated(EnumType.STRING)
    private YN alarmUseYn;                // 알람사용여무(Y/N)
    private String alarmCycle;              // 알람주기(1주, 2주, 3주, 4주)
    private String alarmMainTelephone;  // 알람전송 메인 모바일폰번호
    private String alarmSubTelephone;   // 알람전송 서브 모바일폰번호
    
    // SMS문자 수신시 필터관련 메시지(davada 주류업체만 적용)
    @Enumerated(EnumType.STRING)
    private YN filterUseYn;               // 필터사용여부(Y/N)
    private String filterNormalMsg;         // 필터메시지
    private String filterExceptionMsg;      // 필터제외메시지
}
