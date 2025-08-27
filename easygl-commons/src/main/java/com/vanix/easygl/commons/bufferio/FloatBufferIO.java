package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class FloatBufferIO implements BufferIO<Float> {
    @Override
    public int size() {
        return Float.BYTES;
    }

    @Override
    public void write(@Nonnull Float object, ByteBuffer buffer) {
        buffer.putFloat(object);
    }

    @Override
    public void read(@Nullable Float object, ByteBuffer buffer, Consumer<Float> setter) {
        setter.accept(buffer.getFloat());
    }
}
