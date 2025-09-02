package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DrawMode;
import com.vanix.easygl.core.graphics.VertexArray;
import com.vanix.easygl.core.graphics.VertexAttribute;

import java.util.function.IntConsumer;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    private final VertexAttribute[] attributes = new VertexAttribute[GLX.glGetInteger(GLX.GL_MAX_VERTEX_ATTRIBS)];

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
    public VertexArray bind(int bindingPoint, Buffer buffer, long offset, int stride) {
        assertBinding();
        GLX.glBindVertexBuffer(bindingPoint, buffer.intHandle(), offset, stride);
        GLX.checkError();
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
}
