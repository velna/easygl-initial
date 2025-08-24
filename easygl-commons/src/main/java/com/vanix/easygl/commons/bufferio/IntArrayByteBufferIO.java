package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class IntArrayByteBufferIO implements ByteBufferIO<int[]> {
    @Override
    public void write(@Nonnull int[] object, ByteBuffer buffer) {
        buffer.asIntBuffer().put(object);
    }

    @Override
    public void read(int[] object, ByteBuffer buffer, Consumer<int[]> setter) {
        var intBuffer = buffer.asIntBuffer();
        if (object == null) {
            object = new int[intBuffer.remaining()];
            intBuffer.get(object);
            setter.accept(object);
        } else {
            intBuffer.get(object);
        }
    }
}
