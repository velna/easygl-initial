package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class IntBufferIO implements BufferIO<Integer> {
    @Override
    public int sizeOfOneUnit() {
        return Integer.BYTES;
    }

    @Override
    public void write(@Nonnull Integer object, ByteBuffer buffer) {
        buffer.putInt(object);
    }

    @Override
    public void read(Integer object, ByteBuffer buffer, Consumer<Integer> setter) {
        setter.accept(buffer.getInt());
    }
}
