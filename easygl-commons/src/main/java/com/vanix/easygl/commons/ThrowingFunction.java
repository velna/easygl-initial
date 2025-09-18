package com.vanix.easygl.commons;

public interface ThrowingFunction<T, R, E extends Exception> {
	R apply(T t) throws E;
}
