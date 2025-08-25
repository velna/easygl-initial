package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public interface BufferIO<T> {
    default int sizeOf(Class<T> type) {
        throw new BufferIOException("Unsupported operation.");
    }

    void write(@Nonnull T object, ByteBuffer buffer);

    void read(T object, ByteBuffer buffer, Consumer<T> setter);

    static <T> BufferIO<T> of(Class<T> type) {
        return BufferIORegistry.get(type);
    }
}
