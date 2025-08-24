package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public interface ByteBufferIO<T> {
    void write(@Nonnull T object, ByteBuffer buffer);

    void read(T object, ByteBuffer buffer, Consumer<T> setter);

    static <T> ByteBufferIO<T> of(Class<T> type) {
        return ByteBufferIORegistry.get(type);
    }
}
