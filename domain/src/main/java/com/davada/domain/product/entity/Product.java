package com.davada.domain.product.entity;


import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.product.vo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends AuditableEntity implements Refinable {
    // 품목 UUID
    String productUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    //*상품코드
    String productCode;
    //*상품명
    String productName;
    //별칭
    String productAliasName;
    //주류 매입 단가정보
    ProductPurchaseUnitPrice purchaseUnitPrice;
    //주류 판매 단가정보
    ProductSellingBaseUnitPrice sellingUnitPrice;
    //용기공병정보(+보증금) - Optional: 값이 존재시 엔티티 존재여부 체크한다.
    BeverageContainer beverageContainer;
    //바코드
    String barcode;
    //상자
    String boxBarcode;
    //본바코드
    String bottleBarcode;
    // 주류제조사 UUID
    String supplierUuid;
    //매입처 코드
    String supplierCode;
    //매입처 상호명
    String supplierName;
    //*주류구분
    ProductCategory productCategory;
    //*주종구분
    BeverageCategory beverageCategory;
    //*(주류용도) 상품단가정책: 일반/유흥/공용
    ProductPricePolicy productPricePolicy;
    //*용량
    String volume;
    //*본수(BOX당)
    Integer bottlesInBox;
    //도수
    String alcoholByVolume;
    //상품재고
    Integer stockQuantity;
    //적정재고량
    Integer safetyStock;
    //*재고계산법
    StockCountingMethod stockCountingMethod;
    //*주요상품
    KeyProduct keyProduct;
    // RFID 품목명,
    String rfidProductName;
    //*RFID-BoxTag
    String rfidBoxTag;
    //*RFID-EaTag
    String rfidEaTag;
    //기타(비고)
    String description;

    @Override
    public void refineValues() {
    }
}
