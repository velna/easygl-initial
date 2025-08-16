package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.BufferArray;
import org.lwjgl.system.MemoryStack;

import java.util.List;
import java.util.function.Consumer;

public class GlBufferArray extends BufferArray {
    public GlBufferArray(List<Buffer> list, int[] handles, Consumer<int[]> closeFunction) {
        super(list, handles, closeFunction);
    }

    @Override
    public BufferArray bindBase(int first) {
        Buffer.Type target = getFirst().target();
        GLX.glBindBuffersBase(target.value(), first, handles);
        GLX.checkError();
        return this;
    }

    @Override
    public BufferArray bindRange(int first, long[] offsets, long[] sizes) {
        Buffer.Type target = getFirst().target();
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var offsetsPb = stack.mallocPointer(offsets.length).put(offsets);
            var sizesPb = stack.mallocPointer(sizes.length).put(sizes);
            GLX.glBindBuffersRange(target.value(), first, handles, offsetsPb, sizesPb);
            GLX.checkError();
            return this;
        }
    }
}
