package com.won.board.controller;

import com.won.board.exception.CommonException;
import com.won.board.result.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;


/**
 * Exception 처리
 */
@RestControllerAdvice
public class ExceptionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 컨트롤러 @ModelAttribute, @RequestBody 바인딩 에러 처리
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public Response<?> paramViolationError(Exception ex) {
		String message = "파라미터 바인딩 에러";
		int resultCode = HttpStatus.BAD_REQUEST.value();

		if(ex instanceof ConstraintViolationException) {
			message = ((ConstraintViolationException) ex).getMessage();
			return Response.of(resultCode, message);
		}

		BindingResult bindingResult = ex instanceof BindException
				? ((BindException) ex).getBindingResult()
				: ((MethodArgumentNotValidException) ex).getBindingResult();

		if(bindingResult.hasFieldErrors() && bindingResult.getFieldError() != null) {

			String fieldName = bindingResult.getFieldError().getField();
			Object rejectedValue = bindingResult.getFieldError().getRejectedValue();
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();

			message = fieldName + "=" + rejectedValue + " : " + errorMessage;
		}
		return Response.of(resultCode, message);
	}

	/**
	 * 컨트롤러 @PathVariable 바인딩 에러 처리
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public Response<?> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {

		String errorCode = ex.getErrorCode();
		String paramName = ex.getParameter().getParameterName();
		String paramType = ex.getParameter().getParameterType().getSimpleName();
		String inputValue = ex.getValue() == null ? null : ex.getValue().toString();

		String msg = "code="+errorCode+", param="+paramName+", type="+paramType+", inputValue="+inputValue;

		logger.debug(ex.getMessage());
		logger.debug(msg);

		return Response.of(400, msg);
	}

	/**
	 * 공통 예외처리
	 */
	@ExceptionHandler({CommonException.class})
	public Response<?> commonExceptionHandler(CommonException ex) {
		return ex.getResponse();
	}

	/**
	 * http Content type not supported 처리
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HttpMediaTypeNotSupportedException.class})
	public Response<?> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException ex) {

		logger.debug(ex.getMessage());

		return Response.of(400, ex.getMessage());
	}


	/**
	 * json parse Exception 처리
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public Response<?> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {

		logger.debug(ex.getMessage());

		return Response.of(400, ex.getMessage());
	}

	/**
	 * multipart upload 사이즈 초과 처리
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MaxUploadSizeExceededException.class})
	Response<?> MaxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException ex) {

		logger.debug(ex.getMessage());

		return Response.of(400, ex.getMessage());
	}

	/**
	 * 멀티파트 리퀘스트 필수 파라미터 체크 에러 처리
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MissingServletRequestPartException.class})
	Response<?> MissingServletRequestPartExceptionHandler(MissingServletRequestPartException ex) {

		logger.debug(ex.getMessage());

		return Response.of(400, ex.getMessage());
	}

	/**
	 * 멀티파트 리퀘스트 컨텐츠타입 바운더리 에러 처리
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MultipartException.class})
	Response<?> MultipartExceptionHandler(MultipartException ex) {

		logger.debug(ex.getMessage());

		return Response.of(400, ex.getMessage());
	}







}
