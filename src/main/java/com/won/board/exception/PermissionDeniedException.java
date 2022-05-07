package com.won.board.exception;


import com.won.board.constant.ErrorCode;

/**
 * 리소스 또는 행위에 대한 권한이 없을때 발생.
 */
public class PermissionDeniedException extends CommonException {

    private static final ErrorCode errorCode = ErrorCode.PERMISSION_DENIED;

    public PermissionDeniedException() {
        super(412, errorCode);
    }
}
