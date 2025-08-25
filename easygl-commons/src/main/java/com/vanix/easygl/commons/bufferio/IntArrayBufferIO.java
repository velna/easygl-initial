package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class IntArrayBufferIO implements BufferIO<int[]> {
    @Override
    public int sizeOfOneUnit() {
        return Integer.BYTES;
    }

    @Override
    public void write(@Nonnull int[] object, ByteBuffer buffer) {
        buffer.asIntBuffer().put(object);
    }

    @Override
    public void read(@Nullable int[] object, ByteBuffer buffer, Consumer<int[]> setter) {
        var intBuffer = buffer.asIntBuffer();
        if (object == null) {
            object = new int[intBuffer.remaining()];
            intBuffer.get(object);
            setter.accept(object);
        } else {
            intBuffer.get(object);
        }
    }
}
