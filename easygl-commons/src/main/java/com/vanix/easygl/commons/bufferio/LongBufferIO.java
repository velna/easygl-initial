package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class LongBufferIO implements BufferIO<Long> {
    @Override
    public int size() {
        return Long.BYTES;
    }

    @Override
    public void write(@Nonnull Long object, ByteBuffer buffer) {
        buffer.putLong(object);
    }

    @Override
    public void read(@Nullable Long object, ByteBuffer buffer, Consumer<Long> setter) {
        setter.accept(buffer.getLong());
    }
}
