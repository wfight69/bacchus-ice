package com.davada.domain.wholesaler.entity;

import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.YN;
import lombok.*;

/**
 * 주류도매업체 사용자
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErpUser implements Refinable {
    // ERP 사용자 UUID
    String erpUserUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    // ERP 사용자 Login ID
    String erpUserLoginId;
    //비밀번호
    String password;
    //시스템관리자여부
    YN systemAdmin;
    //시스템접속여부
    YN systemLogin;

    @Override
    public void refineValues() {

    }
}
