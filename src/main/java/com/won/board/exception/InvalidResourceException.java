package com.won.board.exception;

import com.won.board.constant.ErrorCode;

/**
 * 조회된 결과 또는 찾는 리소스가 유효하지 않을때(삭제된 리소스, 잘못된 데이터 등..) 발생
 */
public class InvalidResourceException extends CommonException {

    private static final ErrorCode errorCode = ErrorCode.INVALID_RESOURCE;

    public InvalidResourceException() {
        super(412, errorCode);
    }
}
