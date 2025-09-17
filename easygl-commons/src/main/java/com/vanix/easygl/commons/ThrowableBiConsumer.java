package com.vanix.easygl.commons;

public interface ThrowableBiConsumer<T, U, E extends Throwable> {
    void accept(T t, U u) throws E;
}
