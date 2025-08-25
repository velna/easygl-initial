package com.vanix.easygl.commons.bufferio;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ListBufferIO<T> implements BufferIO<List<T>> {

    private final BufferIO<T> componentBufferIO;
    private final int count;

    public ListBufferIO(BufferIO<T> componentBufferIO, int count) {
        this.componentBufferIO = componentBufferIO;
        this.count = count;
    }

    @Override
    public int sizeOfOneUnit() {
        return componentBufferIO.sizeOfOneUnit();
    }

    @Override
    public void write(@Nonnull List<T> object, ByteBuffer buffer) {
        for (var obj : object) {
            componentBufferIO.write(obj, buffer);
        }
    }

    @Override
    public void read(List<T> object, ByteBuffer buffer, Consumer<List<T>> setter) {
        List<T> list;
        if (object == null) {
            list = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                list.add(null);
            }
        } else {
            list = object;
        }
        for (int i = 0; i < count; i++) {
            int index = i;
            componentBufferIO.read(list.get(i), buffer, v -> list.set(index, v));
        }
        if (list != object) {
            setter.accept(list);
        }
    }
}
