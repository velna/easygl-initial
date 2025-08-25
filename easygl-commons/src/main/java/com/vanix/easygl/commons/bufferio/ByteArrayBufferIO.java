package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ByteArrayBufferIO implements BufferIO<byte[]> {
    @Override
    public int sizeOfOneUnit() {
        return Byte.BYTES;
    }

    @Override
    public void write(@Nonnull byte[] object, ByteBuffer buffer) {
        buffer.put(object);
    }

    @Override
    public void read(@Nullable byte[] object, ByteBuffer buffer, Consumer<byte[]> setter) {
        if (object == null) {
            object = new byte[buffer.remaining()];
            buffer.get(object);
            setter.accept(object);
        } else {
            buffer.get(object);
        }
    }
}
