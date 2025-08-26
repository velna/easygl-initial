package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DataType;
import com.vanix.easygl.core.graphics.DrawMode;
import com.vanix.easygl.core.graphics.VertexArray;

import java.util.function.IntConsumer;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    private int stride;

    GlVertexArray() {
        this(GLX.glGenVertexArrays());
    }

    GlVertexArray(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteVertexArrays);
    }

    @Override
    public VertexArray attributes(Number... layouts) {
        assertBinding();
        int pointer = 0;
        int stride = 0;
        for (var layout : layouts) {
            stride += Math.abs(layout.intValue());
        }
        for (int i = 0; i < layouts.length; i++) {
            var layout = layouts[i];
            int realSize = layout.intValue() & 0x7;
            realSize = layout.intValue() > 0 ? realSize : -realSize;
            boolean signed = Math.abs(layout.intValue()) > 0xf;
            DataType dataType = switch (layout) {
                case Byte b -> signed ? DataType.Byte : DataType.UnsignedByte;
                case Short b -> signed ? DataType.Short : DataType.UnsignedShort;
                case Integer b -> signed ? DataType.Int : DataType.UnsignedInt;
                case Float b -> signed ? DataType.HalfFloat : DataType.Float;
                case Double b -> DataType.Double;
                default -> throw new IllegalArgumentException("Invalid layout size: " + layout);
            };
            if (realSize > 0) {
                GLX.glEnableVertexAttribArray(i);
                GLX.checkError();
                boolean normalized = false;
                if (realSize == 5) {
                    realSize = GLX.GL_BGRA;
                    normalized = true;
                }
                GLX.glVertexAttribPointer(i, realSize, dataType.value(), normalized, stride * dataType.bytes(), pointer);
                GLX.checkError();
            }
            pointer += Math.abs(realSize) * dataType.bytes();
        }
        this.stride = stride;
        return this;
    }

    @Override
    public void drawArray(DrawMode mode, Buffer vbo, int first) {
        bind();
        GLX.glDrawArrays(mode.value(), first, (int) vbo.count() / stride);
        GLX.checkError();
    }

    @Override
    public void drawElements(DrawMode mode, Buffer vbo, Buffer ebo, int indices) {
        bind();
        GLX.glDrawElements(mode.value(), (int) ebo.count(), ebo.dataType().value(), indices);
        GLX.checkError();
    }
}
