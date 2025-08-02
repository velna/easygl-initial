package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.CloseableArray;
import com.vanix.easygl.core.Handle;

public interface HandleMeta<T extends Handle> extends Meta<T> {
    default CloseableArray<T> createArray(int n, Object... args) {
        throw new UnsupportedOperationException();
    }
}
