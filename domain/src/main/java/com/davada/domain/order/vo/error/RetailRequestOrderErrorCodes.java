package com.davada.domain.order.vo.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum RetailRequestOrderErrorCodes implements ErrorCodes {
    REQUEST_ORDER_1000("주문요청을 찾을 수 없습니다.[requestOrderUuid: {0}]"),
    REQUEST_ORDER_1001("주문이 판매되었습니다, 주문삭제는 판매주문을 먼저 취소하십시요."),
    REQUEST_ORDER_2001("주류도매업체를 찾을 수 없습니다.[wholesalerUuid: {0}]"),
    REQUEST_ORDER_2002("주문자의 연락처 정보가 없습니다.[retailOrderTelephone: {0}]"),
    ;

    private String message;

    RetailRequestOrderErrorCodes(String message) {
        this.message = message;
    }

    @Override
    public String errorCode() {
        return name();
    }

    @Override
    public String errorMessage() {
        return this.message;
    }
}
