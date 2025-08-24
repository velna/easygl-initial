package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class DoubleByteBufferIO implements ByteBufferIO<Double> {
    @Override
    public void write(@Nonnull Double object, ByteBuffer buffer) {
        buffer.putDouble(object);
    }

    @Override
    public void read(Double object, ByteBuffer buffer, Consumer<Double> setter) {
        setter.accept(buffer.getDouble());
    }
}
