package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ByteArrayByteBufferIO implements ByteBufferIO<byte[]> {
    @Override
    public void write(@Nonnull byte[] object, ByteBuffer buffer) {
        buffer.put(object);
    }

    @Override
    public void read(byte[] object, ByteBuffer buffer, Consumer<byte[]> setter) {
        if (object == null) {
            object = new byte[buffer.remaining()];
            buffer.get(object);
            setter.accept(object);
        } else {
            buffer.get(object);
        }
    }
}
