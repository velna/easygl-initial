package com.vanix.easygl.commons.bufferio;

import org.joml.Matrix4d;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Matrix4dByteBufferIO implements ByteBufferIO<Matrix4d> {
    @Override
    public void write(@Nonnull Matrix4d object, ByteBuffer buffer) {
        object.get(buffer);
    }

    @Override
    public void read(Matrix4d object, ByteBuffer buffer, Consumer<Matrix4d> setter) {
        if (object == null) {
            object = new Matrix4d(buffer.asDoubleBuffer());
            setter.accept(object);
        } else {
            object.set(buffer);
        }
    }
}
