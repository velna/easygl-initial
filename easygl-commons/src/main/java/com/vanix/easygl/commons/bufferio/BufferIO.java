package com.vanix.easygl.commons.bufferio;

import com.vanix.easygl.commons.util.TypeReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public interface BufferIO<T> {
    int sizeOfOneUnit();

    void write(@Nonnull T object, ByteBuffer buffer);

    void read(@Nullable T object, ByteBuffer buffer, Consumer<T> setter);

    static <T> BufferIO<T> of(T object) {
        return BufferIORegistry.get(object);
    }

    static <T> BufferIO<T> ofType(Class<T> type, int... lengths) {
        return BufferIORegistry.getByType(type, lengths);
    }

    static <T> BufferIO<T> ofType(TypeReference<T> typeReference, int... lengths) {
        return BufferIORegistry.getByType(typeReference, lengths);
    }
}
