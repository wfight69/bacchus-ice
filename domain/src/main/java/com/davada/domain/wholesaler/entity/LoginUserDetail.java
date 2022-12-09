package com.davada.domain.wholesaler.entity;

import com.davada.domain.common.vo.YN;
import com.davada.domain.wholesaler.vo.Van;
import com.davada.domain.wholesaler.vo.VanTerm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserDetail {
    String wholesalerUuid;
    String wholesalerCode;
    String wholesalerName;
    String businessNumber;
    String employeeUuid;
    String employeeCode;
    String employeeName;
    YN systemAdmin;
    YN smsSendYn;
    YN filterUseYn;
    String filterNormalMsg;
    String filterExceptionMsg;
    Van van;
    VanTerm vanTerm;
}
