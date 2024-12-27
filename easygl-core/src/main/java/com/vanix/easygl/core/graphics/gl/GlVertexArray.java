package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.*;

import java.util.stream.IntStream;

public class GlVertexArray extends AbstractBindableHandle<VertexArray> implements VertexArray {

    public GlVertexArray() {
        super(GLC.glGenVertexArrays(), State);
    }

    @Override
    protected void bind(int handle) {
        GLC.glBindVertexArray(handle);
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
    public void draw(DrawMode mode, Buffer vbo) {
        bind();
        vbo.bind();
        GLC.glDrawArrays(mode.value(), 0, vbo.count());
        GLC.checkError();
    }

    @Override
    public void draw(DrawMode mode, Buffer vbo, Buffer ebo) {
        bind();
        GLC.glDrawElements(mode.value(), ebo.count(), ebo.dataType().value(), 0);
        GLC.checkError();
    }
}
