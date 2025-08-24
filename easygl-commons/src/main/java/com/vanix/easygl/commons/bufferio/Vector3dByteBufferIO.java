package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3d;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Vector3dByteBufferIO implements ByteBufferIO<Vector3d> {
    @Override
    public void write(@Nonnull Vector3d object, ByteBuffer buffer) {
        object.get(buffer);
    }

    @Override
    public void read(Vector3d object, ByteBuffer buffer, Consumer<Vector3d> setter) {
        if (object == null) {
            object = new Vector3d(buffer);
            setter.accept(object);
        } else {
            object.set(buffer);
        }
    }
}
