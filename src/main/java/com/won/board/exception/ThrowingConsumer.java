package com.won.board.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {

	static Logger logger = LogManager.getLogger(ThrowingConsumer.class);

	void accept(T t) throws E;

	static <T> Consumer<T> wrapper(ThrowingConsumer<T, Exception> throwingConsumer) {

		Consumer<T> c = i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception e) {
				if(!(e instanceof CommonException)) {
					logger.error(e);
				}
				throw new RuntimeException(e);
			}
		};

		return c;
	}
}
