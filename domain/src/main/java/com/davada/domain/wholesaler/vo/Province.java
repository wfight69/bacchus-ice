package com.davada.domain.wholesaler.vo;

import lombok.Getter;

/**
 * E01	지역구분코드
 */
public enum Province {

    SEOUL("서울"),
    GYEONGGI("경기"),
    INCHEON("인천"),
    DAEJEON("대전"),
    DAEGU("대구"),
    BUSAN("부산"),
    ULSAN("울산"),
    GWANGJU("광주"),
    GANGWON("강원"),
    CHUNGBUK("충북"),
    CHUNGNAM("충남"),
    JEONBUK("전북"),
    JEONNAM("전남"),
    GYEONGBUK("경북"),
    GYEONGNAM("경남"),
    JEJU("제주"),
    OVERSEA("외국");

    @Getter
    private final String description;

    Province(String description) {
        this.description = description;
    }

}
