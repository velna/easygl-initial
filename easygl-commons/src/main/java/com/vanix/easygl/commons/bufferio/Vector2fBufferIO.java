package com.vanix.easygl.commons.bufferio;

import org.joml.Vector2f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Vector2fBufferIO implements BufferIO<Vector2f> {
    private static final int BYTES = Float.BYTES * 2;

    @Override
    public int size() {
        return BYTES;
    }

    @Override
    public void write(@Nonnull Vector2f object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    public void read(@Nullable Vector2f object, ByteBuffer buffer, Consumer<Vector2f> setter) {
        if (object == null) {
            object = new Vector2f(buffer);
            setter.accept(object);
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
    }
}
