package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class ArrayBufferIO<T> implements BufferIO<T[]> {

    private final BufferIO<T> componentBufferIO;
    private final Class<T> componentType;
    private final int count;

    public ArrayBufferIO(BufferIO<T> componentBufferIO, Class<T> componentType, int count) {
        this.componentBufferIO = componentBufferIO;
        this.componentType = componentType;
        this.count = count;
    }

    @Override
    public int sizeOfOneUnit() {
        return componentBufferIO.sizeOfOneUnit();
    }

    @Override
    public void write(@Nonnull T[] object, ByteBuffer buffer) {
        for (var obj : object) {
            componentBufferIO.write(obj, buffer);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(@Nullable T[] object, ByteBuffer buffer, Consumer<T[]> setter) {
        T[] array;
        if (object == null) {
            array = (T[]) Array.newInstance(componentType, count);
        } else {
            array = object;
        }
        for (int i = 0; i < array.length; i++) {
            int index = i;
            componentBufferIO.read(array[i], buffer, v -> array[index] = v);
        }
        if (array != object) {
            setter.accept(array);
        }
    }
}
