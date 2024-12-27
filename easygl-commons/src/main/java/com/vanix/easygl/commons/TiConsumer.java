package com.vanix.easygl.commons;

@FunctionalInterface
public interface TiConsumer<A, B, C> {
	void accept(A a, B b, C c);
}
