package com.davada.domain.board.error;

import com.davada.domain.common.exception.ErrorCodes;

public enum NoticeBoardErrorCodes implements ErrorCodes {
    NOTICE_BOARD_1000("공지사항을 찾을 수 없습니다.[purchaseContainerUuid: {0}]"),
    ;

    private String message;

    NoticeBoardErrorCodes(String message) {
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
