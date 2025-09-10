package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DrawMode;
import com.vanix.easygl.core.graphics.VertexArray;
import com.vanix.easygl.core.graphics.VertexAttribute;
import org.lwjgl.system.MemoryStack;

import java.util.function.IntConsumer;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    private final VertexAttribute[] attributes = new VertexAttribute[MAX_ATTRIBUTES];
    private final BindingPoint[] bindingPoints = new BindingPoint[MAX_BINDING_POINTS];

    GlVertexArray() {
        this(GLX.glGenVertexArrays());
    }

    GlVertexArray(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteVertexArrays);
    }

    @Override
    public VertexAttribute attribute(int index) {
        if (index < 0 || index > attributes.length) {
            throw new IllegalArgumentException("Vertex attribute index must be range from 0 to " + attributes.length);
        }
        var ret = attributes[index];
        if (ret == null) {
            ret = new GlVertexAttribute(index, this);
            attributes[index] = ret;
        }
        return ret;
    }

    @Override
    public BindingPoint bidingPoint(int bindingIndex) {
        if (bindingIndex < 0 || bindingIndex > bindingPoints.length) {
            throw new IllegalArgumentException("Vertex binding index must be range from 0 to " + bindingPoints.length);
        }
        var ret = bindingPoints[bindingIndex];
        if (ret == null) {
            ret = new GlBindingPoint(bindingIndex);
            bindingPoints[bindingIndex] = ret;
        }
        return ret;
    }

    @Override
    public VertexArray bindBuffers(int firstBindingIndex, Iterable<Buffer> bufferIterable, long[] offsets, int[] strides) {
        assertBinding();
        int[] handles;
        if (bufferIterable instanceof HandleArray<Buffer> handleArray) {
            handles = handleArray.getHandles();
        } else {
            handles = new int[offsets.length];
            int i = 0;
            for (Buffer buffer : bufferIterable) {
                handles[i++] = buffer.intHandle();
            }
        }
        try (MemoryStack memoryStack = MemoryStack.stackGet()) {
            var pointerBuffer = memoryStack.mallocPointer(offsets.length);
            pointerBuffer.put(offsets);
            GLX.glBindVertexBuffers(firstBindingIndex, handles, pointerBuffer.clear(), strides);
            GLX.checkError();
        }
        return this;
    }

    @Override
    public VertexArray bindBuffers(int firstBindingIndex, Buffer[] buffers, long[] offsets, int[] strides) {
        assertBinding();
        int[] handles = new int[buffers.length];
        for (int i = 0; i < buffers.length; i++) {
            handles[i] = buffers[i].intHandle();
        }
        try (MemoryStack memoryStack = MemoryStack.stackGet()) {
            var pointerBuffer = memoryStack.mallocPointer(offsets.length);
            pointerBuffer.put(offsets);
            GLX.glBindVertexBuffers(firstBindingIndex, handles, pointerBuffer.clear(), strides);
            GLX.checkError();
        }
        return this;
    }

    @Override
    public void drawArray(DrawMode mode, int first, int count) {
        assertBinding();
        GLX.glDrawArrays(mode.value(), first, count);
        GLX.checkError();
    }

    @Override
    public void drawArrayInstanced(DrawMode mode, int first, int count, int instanceCount) {
        assertBinding();
        GLX.glDrawArraysInstanced(mode.value(), first, count, instanceCount);
        GLX.checkError();
    }

    @Override
    public void drawElements(DrawMode mode, Buffer ebo, int indices) {
        assertBinding();
        GLX.glDrawElements(mode.value(), ebo.count(), ebo.dataType().value(), indices);
        GLX.checkError();
    }

    @Override
    public void drawElementsInstanced(DrawMode mode, Buffer ebo, int indices, int instanceCount) {
        assertBinding();
        GLX.glDrawElementsInstanced(mode.value(), ebo.count(), ebo.dataType().value(), indices, instanceCount);
        GLX.checkError();
    }

    class GlBindingPoint extends SimpleIntEnum implements BindingPoint {
        public GlBindingPoint(int value) {
            super(value);
        }

        @Override
        public BindingPoint bind(Buffer buffer, int offset, int stride) {
            assertBinding();
            GLX.glBindVertexBuffer(value, buffer.intHandle(), offset, stride);
            GLX.checkError();
            return this;
        }

        @Override
        public BindingPoint setDivisor(int divisor) {
            assertBinding();
            GLX.glVertexBindingDivisor(value, divisor);
            GLX.checkError();
            return this;
        }

        @Override
        public int getDivisor() {
            assertBinding();
            return GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_DIVISOR, value);
        }

        @Override
        public int getOffset() {
            assertBinding();
            return GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_OFFSET, value);
        }

        @Override
        public int getStride() {
            assertBinding();
            return GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_STRIDE, value);
        }

        @Override
        public Buffer getBuffer() {
            assertBinding();
            int buffer = GLX.glGetIntegeri(GLX.GL_VERTEX_BINDING_BUFFER, value);
            return buffer > 0 ? GlBuffer.get(buffer) : null;
        }

    }
}
