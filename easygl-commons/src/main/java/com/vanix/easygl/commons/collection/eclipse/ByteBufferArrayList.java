package com.vanix.easygl.commons.collection.eclipse;

import com.vanix.easygl.commons.bufferio.BufferList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteBufferArrayList extends ByteArrayList implements BufferList {
    public ByteBufferArrayList() {
    }

    public ByteBufferArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public ByteBufferArrayList(int initialCapacity, byte fillValue) {
        super(initialCapacity);
        if (fillValue != 0) {
            Arrays.fill(items, fillValue);
        }
        size = initialCapacity;
    }

    public ByteBufferArrayList(byte... array) {
        super(array);
    }

    @Override
    public int saveInto(ByteBuffer buffer) {
        int remaining = buffer.remaining();
        if (size == 0 || remaining == 0) {
            return 0;
        }
        int n = Math.min(size, remaining);
        buffer.put(items, 0, n);
        return n;
    }

    @Override
    public void loadFrom(ByteBuffer buffer) {
        int newSize = Math.max(this.size, buffer.remaining());
        this.ensureCapacity(newSize);
        buffer.get(items, 0, buffer.remaining());
        this.size = newSize;
    }
}
