package com.davada.application.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private Result result;
    private String errorCode;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data, String message) {
        return new CommonResponse<>(Result.SUCCESS, "", message, data);
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, "");
    }

    public static <T> CommonResponse<T> fail(String errorCode, String message) {
        return new CommonResponse<>(Result.FAIL, errorCode, message, null);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
        return new CommonResponse<>(Result.FAIL, errorCode.name(), errorCode.getErrorMsg(), null);
    }

    public enum Result {
        SUCCESS, FAIL
    }

    @JsonIgnore
    public boolean isSuccess() {
        return result == Result.SUCCESS;
    }
}
