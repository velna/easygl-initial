package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.util.TypeReference;

public interface MetaService {
    <T> T of(Class<?> key, TypeReference<T> returnType, Object... args);

    int queryInt(String id);

    int getError();

    void close();
}
