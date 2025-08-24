package com.vanix.easygl.commons.bufferio;

import org.joml.Matrix4f;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Matrix4fByteBufferIO implements ByteBufferIO<Matrix4f> {
    @Override
    public void write(@Nonnull Matrix4f object, ByteBuffer buffer) {
        object.get(buffer);
    }

    @Override
    public void read(Matrix4f object, ByteBuffer buffer, Consumer<Matrix4f> setter) {
        if (object == null) {
            object = new Matrix4f(buffer.asFloatBuffer());
            setter.accept(object);
        } else {
            object.set(buffer);
        }
    }
}
