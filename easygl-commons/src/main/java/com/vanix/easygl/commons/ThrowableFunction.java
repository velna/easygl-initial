package com.vanix.easygl.commons;

public interface ThrowableFunction<T, R, E extends Exception> {
	R apply(T t) throws E;
}
