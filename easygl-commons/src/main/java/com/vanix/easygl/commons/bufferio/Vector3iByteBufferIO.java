package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3i;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Vector3iByteBufferIO implements ByteBufferIO<Vector3i> {
    @Override
    public void write(@Nonnull Vector3i object, ByteBuffer buffer) {
        object.get(buffer);
    }

    @Override
    public void read(Vector3i object, ByteBuffer buffer, Consumer<Vector3i> setter) {
        if (object == null) {
            object = new Vector3i(buffer);
            setter.accept(object);
        } else {
            object.set(buffer);
        }
    }
}
