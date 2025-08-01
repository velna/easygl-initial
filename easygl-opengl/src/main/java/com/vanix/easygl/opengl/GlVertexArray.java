package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.*;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    private static final BindTarget.Default<VertexArray> Target = new BindTarget.Default<>("VertexArray", GlMetaService.VertexArrayMeta);

    GlVertexArray(Object... args) {
        this(GLX.glGenVertexArrays(), args);
    }

    GlVertexArray(int handle, Object... args) {
        super(handle, Target, (IntConsumer) GLX::glDeleteVertexArrays);
    }

    @Override
    public VertexArray attributes(Buffer buffer, int... layouts) {
        assertBinding();
        buffer.bind();
        int pointer = 0;
        int stride = IntStream.of(layouts).sum();
        DataType dataType = buffer.dataType();
        for (int i = 0; i < layouts.length; i++) {
            GLX.glEnableVertexAttribArray(i);
            GLX.checkError();
            GLX.glVertexAttribPointer(i, layouts[i], dataType.value(), false, stride * dataType.bytes(), pointer);
            pointer += layouts[i] * dataType.bytes();
            GLX.checkError();
        }
        return this;
    }

    @Override
    public void drawArray(DrawMode mode, Buffer vbo, int first) {
        bind();
        GLX.glDrawArrays(mode.value(), first, vbo.count());
        GLX.checkError();
    }

    @Override
    public void drawElements(DrawMode mode, Buffer vbo, Buffer ebo, int indices) {
        bind();
        GLX.glDrawElements(mode.value(), ebo.count(), ebo.dataType().value(), indices);
        GLX.checkError();
    }
}
