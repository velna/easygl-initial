package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ByteBufferIO implements BufferIO<Byte> {
    @Override
    public int sizeOfOneUnit() {
        return Byte.BYTES;
    }

    @Override
    public void write(@Nonnull Byte object, ByteBuffer buffer) {
        buffer.put(object);
    }

    @Override
    public void read(Byte object, ByteBuffer buffer, Consumer<Byte> setter) {
        setter.accept(buffer.get());
    }
}
