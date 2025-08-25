package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class DoubleArrayBufferIO implements BufferIO<double[]> {
    @Override
    public int sizeOfOneUnit() {
        return Double.BYTES;
    }

    @Override
    public void write(@Nonnull double[] object, ByteBuffer buffer) {
        buffer.asDoubleBuffer().put(object);
    }

    @Override
    public void read(@Nullable double[] object, ByteBuffer buffer, Consumer<double[]> setter) {
        var doubleBuffer = buffer.asDoubleBuffer();
        if (object == null) {
            object = new double[doubleBuffer.remaining()];
            doubleBuffer.get(object);
            setter.accept(object);
        } else {
            doubleBuffer.get(object);
        }
    }
}
