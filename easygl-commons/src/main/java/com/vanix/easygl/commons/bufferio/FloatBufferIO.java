package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class FloatBufferIO implements BufferIO<Float> {
    @Override
    public int sizeOfOneUnit() {
        return Float.BYTES;
    }

    @Override
    public void write(@Nonnull Float object, ByteBuffer buffer) {
        buffer.putFloat(object);
    }

    @Override
    public void read(Float object, ByteBuffer buffer, Consumer<Float> setter) {
        setter.accept(buffer.getFloat());
    }
}
