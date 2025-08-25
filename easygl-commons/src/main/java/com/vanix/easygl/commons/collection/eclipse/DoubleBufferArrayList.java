package com.vanix.easygl.commons.collection.eclipse;

import com.vanix.easygl.commons.bufferio.BufferList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DoubleBufferArrayList extends DoubleArrayList implements BufferList {
    public DoubleBufferArrayList() {
    }

    public DoubleBufferArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public DoubleBufferArrayList(int initialCapacity, double fillValue) {
        super(initialCapacity);
        if (fillValue != 0) {
            Arrays.fill(items, fillValue);
        }
        size = initialCapacity;
    }

    public DoubleBufferArrayList(double... array) {
        super(array);
    }

    @Override
    public int saveInto(ByteBuffer buffer) {
        int remaining = buffer.remaining();
        if (size == 0 || remaining == 0) {
            return 0;
        }
        int n = Math.min(size, remaining);
        buffer.asDoubleBuffer().put(items, 0, n);
        return n;
    }

    @Override
    public void loadFrom(ByteBuffer buffer) {
        int newSize = Math.max(this.size, buffer.remaining());
        this.ensureCapacity(newSize);
        buffer.asDoubleBuffer().get(items, 0, buffer.remaining());
        this.size = newSize;
    }
}
