package com.davada.domain.wholesaler.vo;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Van {
    private VanType vanCompany;    // van업체정보(ex. ksnet, ktft(금결원))
    private String bankCode; // 은행코드(ex. 88:신한, 38:농협)
    private String vanNumber; // 가맹점번호
    private String vanCredential; // 가맹점패스워드
}
