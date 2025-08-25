package com.vanix.easygl.commons.bufferio;

import com.vanix.easygl.commons.util.TypeReference;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public interface BufferIO<T> {
    int sizeOfOneUnit();

    void write(@Nonnull T object, ByteBuffer buffer);

    void read(T object, ByteBuffer buffer, Consumer<T> setter);

    static <T> BufferIO<T> of(Class<T> type, int... lengths) {
        return BufferIORegistry.get(type, lengths);
    }

    static <T> BufferIO<T> of(TypeReference<T> typeReference, int... lengths) {
        return BufferIORegistry.get(typeReference, lengths);
    }
}
