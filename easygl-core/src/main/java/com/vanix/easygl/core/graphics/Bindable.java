package com.vanix.easygl.core.graphics;

import java.util.function.Consumer;

import com.vanix.easygl.commons.ThrowableConsumer;

public interface Bindable<T extends Bindable<T>> {
	T bind();

	T unbind();

	T assertBinding() throws IllegalStateException;

	@SuppressWarnings("unchecked")
	default T use(Consumer<T> callback) {
		bind();
		try {
			callback.accept((T) this);
		} finally {
			unbind();
		}
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	default <E extends Throwable> T useOrThrow(ThrowableConsumer<T, E> callback) throws E {
		bind();
		try {
			callback.accept((T) this);
		} finally {
			unbind();
		}
		return (T) this;
	}

}
