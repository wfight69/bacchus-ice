package com.davada.dto.retailshop;


import com.davada.domain.common.vo.YN;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailOrderTelephoneDto {
    String retailOrderTelephoneUuid;
    String retailShopUuid;
    String retailShopName;
    String telephone;
    String description;
    YN useYn;
}
