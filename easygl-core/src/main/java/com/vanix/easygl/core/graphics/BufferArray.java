package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.CloseableArray;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class BufferArray extends CloseableArray<Buffer> {
    public BufferArray(List<Buffer> list, int[] handles, Consumer<int[]> closeFunction) {
        super(list, handles, closeFunction);
    }

    public abstract BufferArray bindBase(int first);

    public abstract BufferArray bindRange(int first, long[] offsets, long[] sizes);

    public BufferArray bindRange(int first, long offset, long size) {
        long[] offsets = new long[size()];
        Arrays.fill(offsets, offset);
        long[] sizes = new long[size()];
        Arrays.fill(sizes, size);
        return bindRange(first, offsets, sizes);
    }
}
