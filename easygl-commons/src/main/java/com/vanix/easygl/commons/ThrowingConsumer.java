package com.vanix.easygl.commons;

public interface ThrowingConsumer<T, E extends Throwable> {
	void accept(T t) throws E;
}
