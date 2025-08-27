package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public abstract class AbstractMultiElementBufferIO<T> implements BufferIO<T> {
    protected final int size;
    protected final int count;

    protected AbstractMultiElementBufferIO(int count, int unitSize) {
        size = count * unitSize;
        this.count = count;
    }

    protected abstract int countOf(T object);

    protected abstract T create(int count);

    protected abstract void doWrite(@Nonnull T object, ByteBuffer buffer);

    protected abstract void doRead(@Nonnull T object, ByteBuffer buffer);

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final void write(@Nonnull T object, ByteBuffer buffer) {
        check(object);
        int pos = buffer.position();
        doWrite(object, buffer);
        if (buffer.position() - pos != size) {
            throw new BufferIOException("Expect write " + size + " bytes, but only " + (buffer.position() - pos) + " detected.");
        }
    }

    @Override
    public final void read(@Nullable T object, ByteBuffer buffer, Consumer<T> setter) {
        T value = object;
        if (value == null) {
            value = create(count);
        }
        int pos = buffer.position();
        doRead(value, buffer);
        if (buffer.position() - pos != size) {
            throw new BufferIOException("Expect read " + size + " bytes, but only " + (buffer.position() - pos) + " detected.");
        }
        check(value);
        if (value != object) {
            setter.accept(value);
        }
    }

    private void check(T object) {
        int c = countOf(object);
        if (c != count) {
            throw new BufferIOException("Expect " + count + " elements buf found " + c);
        }
    }
}
