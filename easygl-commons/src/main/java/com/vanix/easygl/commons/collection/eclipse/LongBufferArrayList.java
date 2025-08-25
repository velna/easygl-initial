package com.vanix.easygl.commons.collection.eclipse;

import com.vanix.easygl.commons.bufferio.BufferList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class LongBufferArrayList extends LongArrayList implements BufferList {
    public LongBufferArrayList() {
    }

    public LongBufferArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public LongBufferArrayList(int initialCapacity, long fillValue) {
        super(initialCapacity);
        if (fillValue != 0) {
            Arrays.fill(items, fillValue);
        }
        size = initialCapacity;
    }

    public LongBufferArrayList(long... array) {
        super(array);
    }

    @Override
    public int saveInto(ByteBuffer buffer) {
        int remaining = buffer.remaining();
        if (size == 0 || remaining == 0) {
            return 0;
        }
        int n = Math.min(size, remaining);
        buffer.asLongBuffer().put(items, 0, n);
        return n;
    }

    @Override
    public void loadFrom(ByteBuffer buffer) {
        int newSize = Math.max(this.size, buffer.remaining());
        this.ensureCapacity(newSize);
        buffer.asLongBuffer().get(items, 0, buffer.remaining());
        this.size = newSize;
    }
}
