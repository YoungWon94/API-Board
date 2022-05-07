
package com.won.board.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {

	static Logger logger = LogManager.getLogger(ThrowingFunction.class);

	public R apply(T t) throws E;

	public static <T, R, E extends Exception> Function<T, R> wrapper(ThrowingFunction<T, R, Exception> function) {

		Function<T, R> f = i -> {
			try {
				return function.apply(i);
			} catch (Exception e) {
				if(e instanceof CommonException == false) {
					logger.error(e);
				}
				throw new RuntimeException(e);
			}
		};
		return f;

//	        return arg -> {
//	            try {
//	                return fe.apply(arg);
//	            } catch (Exception e) {
//	                throw new RuntimeException(e);
//	            }
//	        };
	}

}