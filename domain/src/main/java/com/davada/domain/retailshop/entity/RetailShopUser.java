package com.davada.domain.retailshop.entity;

import com.davada.domain.common.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 소매업체 사용자
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailShopUser implements Entity {
    // 사용자 UUID
    String retailShopUserUuid;
    // 소매업체 UUID
    String wholesalerUuid;
    // 사용자 Login ID
    String userLoginId;
    // 비밀번호
    String password;
    // 전화번호
    // 이름

}
