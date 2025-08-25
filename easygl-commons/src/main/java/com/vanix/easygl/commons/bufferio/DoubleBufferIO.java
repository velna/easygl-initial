package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class DoubleBufferIO implements BufferIO<Double> {
    @Override
    public int sizeOfOneUnit() {
        return Double.BYTES;
    }

    @Override
    public void write(@Nonnull Double object, ByteBuffer buffer) {
        buffer.putDouble(object);
    }

    @Override
    public void read(@Nullable Double object, ByteBuffer buffer, Consumer<Double> setter) {
        setter.accept(buffer.getDouble());
    }
}
