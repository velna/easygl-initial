package com.vanix.easygl.core.meta;

import com.vanix.easygl.commons.util.TypeReference;

public interface MetaService {
    <T> T of(Class<?> key, TypeReference<T> returnType, Object... args);

    default <T> T of(Class<?> key, Object... args) {
        return of(key, (TypeReference<T>) null, args);
    }

    default <T> T of(Class<?> key, Class<T> returnType, Object... args) {
        return of(key, (TypeReference<T>) null, args);
    }

    int queryInt(String id);

    default Object[] queryArray(String id) {
        throw new UnsupportedOperationException();
    }

    int getError();

    void close();
}
