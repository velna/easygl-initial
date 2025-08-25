package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class FloatArrayBufferIO implements BufferIO<float[]> {
    @Override
    public void write(@Nonnull float[] object, ByteBuffer buffer) {
        buffer.asFloatBuffer().put(object);
    }

    @Override
    public void read(float[] object, ByteBuffer buffer, Consumer<float[]> setter) {
        var floatBuffer = buffer.asFloatBuffer();
        if (object == null) {
            object = new float[floatBuffer.remaining()];
            floatBuffer.get(object);
            setter.accept(object);
        } else {
            floatBuffer.get(object);
        }
    }
}
