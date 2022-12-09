package com.davada.domain.payment.entity;

import com.davada.domain.common.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaxInvoiceParty implements Entity {
    String taxInvoicePartyUuid;
    // 관리번호(24자리이내) O: 공급자의 관리번호 입력, 공급받는자의 관리번호는 빈 값 처리
    // wholesalerUuid 앞자리 20자 without dash (1ed33fdad4d16be58135)
    String mgtNum;
    // 사업자번호 O
    String corpNum;
    // 종사업장 식별번호
    String taxRegID;
    // 회사명 O
    String corpName;
    // 대표자명 O
    String ceoName;
    // 주소 O
    String addr;
    // 업종
    String bizClass;
    // 업태
    String bizType;
    // 바로빌 회원 아이디 O : 공급자의 바로빌 아이디 입력, 공급받는자의 바로빌 아이디는 빈 값 처리
    String contactID;
    // 담당자명 O
    String contactName;
    // 전화번호
    String tel;
    // 휴대폰
    String hp;
    // 이메일 O
    String email;
}
