package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ShortArrayBufferIO implements BufferIO<short[]> {
    @Override
    public int sizeOfOneUnit() {
        return Short.BYTES;
    }

    @Override
    public void write(@Nonnull short[] object, ByteBuffer buffer) {
        buffer.asShortBuffer().put(object);
    }

    @Override
    public void read(@Nullable short[] object, ByteBuffer buffer, Consumer<short[]> setter) {
        var shortBuffer = buffer.asShortBuffer();
        if (object == null) {
            object = new short[shortBuffer.remaining()];
            shortBuffer.get(object);
            setter.accept(object);
        } else {
            shortBuffer.get(object);
        }
    }
}
