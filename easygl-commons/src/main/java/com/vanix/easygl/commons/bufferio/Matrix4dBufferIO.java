package com.vanix.easygl.commons.bufferio;

import org.joml.Matrix4d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Matrix4dBufferIO implements BufferIO<Matrix4d> {
    private static final int BYTES = Double.BYTES << 4;

    @Override
    public int size() {
        return BYTES;
    }

    @Override
    public void write(@Nonnull Matrix4d object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    public void read(@Nullable Matrix4d object, ByteBuffer buffer, Consumer<Matrix4d> setter) {
        if (object == null) {
            object = new Matrix4d(buffer.asDoubleBuffer());
            setter.accept(object);
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
    }
}
