package com.davada.domain.product.entity;

import com.davada.domain.common.AuditableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RFIDProduct extends AuditableEntity {
    // RFID 품목 UUID
    String rfidProductUuid;
    // 주류도매업체 UUID
//    String wholesalerUuid;
    // RFID 품목명,
    String rfidProductName;
    // 제조사코드,
    String manufactureCode;
    // box태그,
    String rfidBoxTag;
    // Ea태그
    String rfidEaTag;
    // 정렬순서
    // Integer orderSequence;
}
