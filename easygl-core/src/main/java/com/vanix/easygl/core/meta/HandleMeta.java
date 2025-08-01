package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.Handle;

public interface HandleMeta<T extends Handle> {
    T create(Object... args);

    CloseableArray<T> createArray(int n, Object... args);
}
