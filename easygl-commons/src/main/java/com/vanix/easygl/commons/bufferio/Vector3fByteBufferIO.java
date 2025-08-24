package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3f;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Vector3fByteBufferIO implements ByteBufferIO<Vector3f> {
    @Override
    public void write(@Nonnull Vector3f object, ByteBuffer buffer) {
        object.get(buffer);
    }

    @Override
    public void read(Vector3f object, ByteBuffer buffer, Consumer<Vector3f> setter) {
        if (object == null) {
            object = new Vector3f(buffer);
            setter.accept(object);
        } else {
            object.set(buffer);
        }
    }
}
