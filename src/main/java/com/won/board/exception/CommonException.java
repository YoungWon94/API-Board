package com.won.board.exception;

import com.won.board.constant.ErrorCode;
import com.won.board.controller.ExceptionController;
import com.won.board.result.Response;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Optional;


/**
 * 에러 처리를 위한 예외 클래스.
 * {@link ExceptionController}에서 공통으로 처리하기 위해 에러코드와 에러메세지를 강제함.
 * @author choiyoung-won
 *
 */
public class CommonException extends Exception {

	private static final long serialVersionUID = -8388293904705324746L;
	private Response<?> response;


	//===== 생성 메서드 =====//

	/**
	 * constructor
	 * @param code 에러코드
	 * @param message 에러메세지
	 */
	public CommonException(int code, String message) {
		super(message);
		response = Response.of(code, message);
	}

	/**
	 * constructor. resultCode는 412로 고정
	 * @param message 에러메세지
	 */
	public CommonException(String message) {
		this(412, message);
	}

	/**
	 * constructor
	 * @param resultCode 결과코드
	 * @param errorCode 에러코드
	 */
	public CommonException(int resultCode, @NonNull ErrorCode errorCode) {
		HashMap<String, Object> resultData = new HashMap<>();
		resultData.put("errorCode", errorCode.getCode());
		response = Response.of(resultCode, errorCode.getMessage(), resultData);
	}

	/**
	 * 응답 반환
	 */
	public Response<?> getResponse() {
		return Optional.ofNullable(response).orElseThrow(()->new NullPointerException("response is null"));
	}

	/**
	 * 에러 reusltData 셋팅
	 */
	protected void setErrorData(@NonNull ErrorCode errorCode) {
		HashMap<String, Object> resultData = new HashMap<>();
		resultData.put("errorCode", errorCode.getCode());
		resultData.put("errorMessage", errorCode.getMessage());
		response = response == null
				? Response.of(999, errorCode.getMessage(), resultData)
				: Response.of(response.getResultCode(), response.getResultMsg(), resultData);
	}

}
