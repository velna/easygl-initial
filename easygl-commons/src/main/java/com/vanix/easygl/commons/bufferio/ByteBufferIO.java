package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ByteBufferIO implements BufferIO<Byte> {
    @Override
    public int size() {
        return Byte.BYTES;
    }

    @Override
    public void write(@Nonnull Byte object, ByteBuffer buffer) {
        buffer.put(object);
    }

    @Override
    public void read(@Nullable Byte object, ByteBuffer buffer, Consumer<Byte> setter) {
        setter.accept(buffer.get());
    }
}
