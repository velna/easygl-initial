package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class LongArrayBufferIO implements BufferIO<long[]> {
    @Override
    public int sizeOfOneUnit() {
        return Long.BYTES;
    }

    @Override
    public void write(@Nonnull long[] object, ByteBuffer buffer) {
        buffer.asLongBuffer().put(object);
    }

    @Override
    public void read(long[] object, ByteBuffer buffer, Consumer<long[]> setter) {
        var longBuffer = buffer.asLongBuffer();
        if (object == null) {
            object = new long[longBuffer.remaining()];
            longBuffer.get(object);
            setter.accept(object);
        } else {
            longBuffer.get(object);
        }
    }
}
