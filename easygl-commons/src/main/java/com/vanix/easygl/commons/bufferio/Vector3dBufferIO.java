package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Vector3dBufferIO implements BufferIO<Vector3d> {
    private static final int BYTES = Double.BYTES * 3;

    @Override
    public int size() {
        return BYTES;
    }

    @Override
    public void write(@Nonnull Vector3d object, ByteBuffer buffer) {
        object.get(buffer).position(buffer.position() + BYTES);
    }

    @Override
    public void read(@Nullable Vector3d object, ByteBuffer buffer, Consumer<Vector3d> setter) {
        if (object == null) {
            object = new Vector3d(buffer);
            setter.accept(object);
        } else {
            object.set(buffer);
        }
        buffer.position(buffer.position() + BYTES);
    }
}
