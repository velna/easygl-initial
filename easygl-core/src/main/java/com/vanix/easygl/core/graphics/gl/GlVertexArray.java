package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.*;

import java.util.stream.IntStream;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    public GlVertexArray() {
        super(GLC.glGenVertexArrays(), Target);
    }

    @Override
    protected void close(int handle) {
        GLC.glDeleteVertexArrays(handle);
    }

    @Override
    public VertexArray attributes(Buffer buffer, int... layouts) {
        assertBinding();
        buffer.bind();
        int pointer = 0;
        int stride = IntStream.of(layouts).sum();
        DataType dataType = buffer.dataType();
        for (int i = 0; i < layouts.length; i++) {
            GLC.glEnableVertexAttribArray(i);
            GLC.checkError();
            GLC.glVertexAttribPointer(i, layouts[i], dataType.value(), false, stride * dataType.bytes(), pointer);
            pointer += layouts[i] * dataType.bytes();
            GLC.checkError();
        }
        return this;
    }

    @Override
    public void drawArray(DrawMode mode, Buffer vbo, int first) {
        bind();
        vbo.bind();
        GLC.glDrawArrays(mode.value(), first, vbo.count());
        GLC.checkError();
    }

    @Override
    public void drawElements(DrawMode mode, Buffer vbo, Buffer ebo, int indices) {
        bind();
        GLC.glDrawElements(mode.value(), ebo.count(), ebo.dataType().value(), indices);
        GLC.checkError();
    }
}
