package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ShortBufferIO implements BufferIO<Short> {
    @Override
    public int sizeOf(Class<Short> type) {
        return Short.BYTES;
    }

    @Override
    public void write(@Nonnull Short object, ByteBuffer buffer) {
        buffer.putShort(object);
    }

    @Override
    public void read(Short object, ByteBuffer buffer, Consumer<Short> setter) {
        setter.accept(buffer.getShort());
    }
}
