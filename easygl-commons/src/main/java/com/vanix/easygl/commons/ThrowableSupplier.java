package com.vanix.easygl.commons;

public interface ThrowableSupplier<T, E extends Throwable> {
    T get() throws E;
}
