/**
 *
 */
package com.won.board.config.aop;

import com.won.board.result.Response;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 로깅 AOP
 */
@Aspect
@Component
@Order(1)
@RequiredArgsConstructor
public class LoggingAop {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final HttpServletRequest request;

	/**
	 * API 시간 측정 포인트컷
	 */
	@Pointcut("execution(* com.won.board.controller..*.*(..))"
			+ "&& !execution(* com.won.board..controller.ExceptionController.*(..))")
	private void timeLoggingPointCut() {}

//	/**
//	 * API 결과 로깅 포인트컷
//	 */
//	@Pointcut("execution(* com.won.board.controller..*.*(..))")
//	private void resultLoggingPointCut() {}

	/**
	 * API 시간 측정
	 */
	@Around("timeLoggingPointCut()")
	private Response<?> timeLogging(ProceedingJoinPoint pjp) throws Throwable {

		long start = System.currentTimeMillis();

		Response<?> response = null;
		try {
			response = (Response<?>) pjp.proceed();
		}catch(Exception e) {
			//Exception 발생 시 ExceptionController 가 핸들링 하므로 시간 로깅이 되지 않아 캐치 해서 로깅 후 다시 던짐.
			long end = System.currentTimeMillis();
			logger.info("===== API({}) 걸린시간 : {}초", request.getRequestURI(), (end-start)/1000.0);
			throw e;
		}
		long end = System.currentTimeMillis();

		logger.info("===== API({}) 걸린시간 : {}초", request.getRequestURI(), (end-start)/1000.0);

		return response;

	}

//	/**
//	 * API 결과 로깅
//	 */
//	@AfterReturning(value = "resultLoggingPointCut()", returning = "response")
//	private void bizExceptionLogging(Response<?> response) throws Throwable{
//
//		if(response == null) {
//			logger.debug("response is null");
//		}else if(response.getResultCode() != 200) {
//			logger.debug("resultCode : {}, message : {}", response.getResultCode(), response.getResultMsg());
//		}
//	}



}
