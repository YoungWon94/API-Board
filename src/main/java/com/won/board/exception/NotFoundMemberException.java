package com.won.board.exception;

import com.won.board.constant.ErrorCode;

/**
 * 조회된 회원이 없는경우 발생하는 예외
 */
public class NotFoundMemberException extends CommonException {

    private static final ErrorCode errorCode = ErrorCode.NOT_FOUND_MEMBER;

    public NotFoundMemberException() {
        super(400, errorCode);
    }
}
