package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DataType;
import com.vanix.easygl.core.graphics.DrawMode;
import com.vanix.easygl.core.graphics.VertexArray;

import java.util.function.IntConsumer;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    GlVertexArray() {
        this(GLX.glGenVertexArrays());
    }

    GlVertexArray(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteVertexArrays);
    }

    @Override
    public VertexArray attributes(Buffer buffer, int... layouts) {
        assertBinding();
        buffer.bind();
        int pointer = 0;
        int stride = 0;
        for (int layout : layouts) {
            stride += Math.abs(layout);
        }
        DataType dataType = buffer.dataType();
        for (int i = 0; i < layouts.length; i++) {
            int layout = layouts[i];
            if (layout > 0) {
                GLX.glEnableVertexAttribArray(i);
                GLX.checkError();
                GLX.glVertexAttribPointer(i, layout, dataType.value(), false, stride * dataType.bytes(), pointer);
                pointer += layout * dataType.bytes();
                GLX.checkError();
            }
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
