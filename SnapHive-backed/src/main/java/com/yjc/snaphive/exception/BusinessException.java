package com.yjc.snaphive.exception;

import lombok.Getter;

/**
 * 自定义业务异�?
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误�?
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
