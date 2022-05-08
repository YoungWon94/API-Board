package com.won.board.exception;

import com.won.board.constant.ErrorCode;

/**
 * 이미 사용중인 ID에 대한 예외
 */
public class AlreadyInUseIdException extends CommonException {

    private static final ErrorCode errorCode = ErrorCode.ALREADY_USE_IN_ID;

    public AlreadyInUseIdException() {
        super(400, errorCode);
    }
}
