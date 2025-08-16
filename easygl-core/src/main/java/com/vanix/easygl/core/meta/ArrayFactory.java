package com.vanix.easygl.core.meta;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.CloseableArray;

import java.util.List;
import java.util.function.Consumer;

public interface ArrayFactory<T extends Closeable> {
    CloseableArray<T> createArray(List<T> list, int[] handles, Consumer<int[]> closeFunction);
}
