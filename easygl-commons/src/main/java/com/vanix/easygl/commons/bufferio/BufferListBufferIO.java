package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public class BufferListBufferIO<T extends BufferList> implements BufferIO<T> {
    private final int count;
    private final int sizeOfUnit;
    private final IntFunction<T> factory;

    protected BufferListBufferIO(int count, int sizeOfUnit, IntFunction<T> factory) {
        this.count = count;
        this.sizeOfUnit = sizeOfUnit;
        this.factory = factory;
    }

    private void checkSize(T list) {
        if (list != null && list.size() != count) {
            throw new BufferIOException("Expect size of " + count + " but found " + list.size());
        }
    }

    @Override
    public int sizeOfOneUnit() {
        return sizeOfUnit;
    }

    @Override
    public void write(@Nonnull T object, ByteBuffer buffer) {
        checkSize(object);
        object.saveInto(buffer);
    }

    @Override
    public void read(@Nullable T object, ByteBuffer buffer, Consumer<T> setter) {
        checkSize(object);
        T list = object == null ? factory.apply(count) : object;
        list.loadFrom(buffer);
        if (object != list) {
            setter.accept(list);
        }
    }
}
