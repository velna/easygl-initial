package com.vanix.easygl.commons.bufferio;

import org.joml.Matrix4f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Matrix4FBufferIO implements BufferIO<Matrix4f> {
    private static final int BYTES = Float.BYTES << 4;

    @Override
    public int sizeOfOneUnit() {
        return BYTES;
    }

    @Override
    public void write(@Nonnull Matrix4f object, ByteBuffer buffer) {
        object.get(buffer);
    }

    @Override
    public void read(@Nullable Matrix4f object, ByteBuffer buffer, Consumer<Matrix4f> setter) {
        if (object == null) {
            object = new Matrix4f(buffer.asFloatBuffer());
            setter.accept(object);
        } else {
            object.set(buffer);
        }
    }
}
