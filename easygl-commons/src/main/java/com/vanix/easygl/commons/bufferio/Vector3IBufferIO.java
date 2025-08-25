package com.vanix.easygl.commons.bufferio;

import org.joml.Vector3i;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Vector3IBufferIO implements BufferIO<Vector3i> {
    private static final int BYTES = Integer.BYTES * 3;

    @Override
    public int sizeOfOneUnit() {
        return BYTES;
    }

    @Override
    public void write(@Nonnull Vector3i object, ByteBuffer buffer) {
        object.get(buffer);
    }

    @Override
    public void read(@Nullable Vector3i object, ByteBuffer buffer, Consumer<Vector3i> setter) {
        if (object == null) {
            object = new Vector3i(buffer);
            setter.accept(object);
        } else {
            object.set(buffer);
        }
    }
}
