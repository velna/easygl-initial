package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class LongByteBufferIO implements ByteBufferIO<Long> {
    @Override
    public void write(@Nonnull Long object, ByteBuffer buffer) {
        buffer.putLong(object);
    }

    @Override
    public void read(Long object, ByteBuffer buffer, Consumer<Long> setter) {
        setter.accept(buffer.getLong());
    }
}
