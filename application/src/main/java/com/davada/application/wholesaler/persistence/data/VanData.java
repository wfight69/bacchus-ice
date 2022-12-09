package com.davada.application.wholesaler.persistence.data;

import com.davada.domain.wholesaler.vo.VanType;
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
public class VanData {
    @Enumerated(EnumType.STRING)
    private VanType vanCompany;    // van업체정보(ex. ksnet, ktft(금결원))
    private String bankCode; // 은행코드(ex. 88:신한, 38:농협)
    private String vanNumber; // 가맹점번호
    private String vanCredential; // 가맹점패스워드
}
