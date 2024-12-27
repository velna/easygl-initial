package com.vanix.easygl.commons.event;

@FunctionalInterface
public interface ListenerKey<T> {
	int value();

	static <T> ListenerKey<T> of(int v) {
		return () -> v;
	}
}
