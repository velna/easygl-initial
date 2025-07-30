package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.ThrowableConsumer;

import java.util.function.Consumer;

public interface Bindable<E extends BindTarget<E, T>, T extends Bindable<E, T>> extends Handle {
    E target();

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
    default <R extends Throwable> T useOrThrow(ThrowableConsumer<T, R> callback) throws R {
        bind();
        try {
            callback.accept((T) this);
        } finally {
            unbind();
        }
        return (T) this;
    }

}
