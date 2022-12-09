package com.davada.domain.order.vo;

import lombok.Getter;

/**
 * 주문 INPUT 경로
 */
public enum RetailOrderChannel {
    VOICE("음성"), // 전화로 음성 주문 요청
    SMS("SMS"), // 문자로 텍스트 주문 요청
    KAKAO("카톡"), // 카카오톡으로 텍스트 주문 요청
    APP("코드"), // 소매점에서 모바일 앱으로 주문 등록
    DIRECT("담당"); // 주류도매업체 담당자가 직접 주문 등록

    @Getter
    private final String description;

    RetailOrderChannel(String description) {
        this.description = description;
    }
}