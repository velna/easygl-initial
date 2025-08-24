package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ShortByteBufferIO implements ByteBufferIO<Short> {
    @Override
    public void write(@Nonnull Short object, ByteBuffer buffer) {
        buffer.putShort(object);
    }

    @Override
    public void read(Short object, ByteBuffer buffer, Consumer<Short> setter) {
        setter.accept(buffer.getShort());
    }
}
