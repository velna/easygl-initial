package com.vanix.easygl.commons;

public interface ThrowingSupplier<T, E extends Throwable> {
    T get() throws E;
}
