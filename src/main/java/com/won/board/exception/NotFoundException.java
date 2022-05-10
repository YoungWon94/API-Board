package com.won.board.exception;

import com.won.board.constant.ErrorCode;

/**
 * 조회된 결과 또는 찾는 리소스가 없는 경우 발생
 */
public class NotFoundException extends CommonException {

    private static final ErrorCode errorCode = ErrorCode.NOT_FOUND;

    public NotFoundException() {
        super(400, errorCode);
    }

    public NotFoundException(int code, String message) {
        super(400, message);
        super.setErrorData(errorCode);
    }


}
