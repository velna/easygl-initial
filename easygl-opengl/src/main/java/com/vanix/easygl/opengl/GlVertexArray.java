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
        int strideBytes = 0;
        DataType[] dataTypes = new DataType[layouts.length];
        for (int i = 0; i < layouts.length; i++) {
            var layout = layouts[i];
            var realSize = Math.abs(layout.intValue() % 10);
            if (realSize >= 5) {
                realSize = 4;
            }
            stride += realSize;
            boolean signed = Math.abs(layout.intValue()) > 10;
            dataTypes[i] = switch (layout) {
                case Byte b -> signed ? DataType.Byte : DataType.UnsignedByte;
                case Short b -> signed ? DataType.Short : DataType.UnsignedShort;
                case Integer b -> signed ? DataType.Int : DataType.UnsignedInt;
                case Float b -> signed ? DataType.HalfFloat : DataType.Float;
                case Double b -> DataType.Double;
                default -> throw new IllegalArgumentException("Invalid layout size: " + layout);
            };
            strideBytes += realSize * dataTypes[i].bytes();
        }

        for (int i = 0; i < layouts.length; i++) {
            var layout = layouts[i];
            int realSize = layout.intValue() % 10;
            realSize = layout.intValue() > 0 ? realSize : -realSize;
            DataType dataType = dataTypes[i];
            if (realSize > 0) {
                GLX.glEnableVertexAttribArray(i);
                GLX.checkError();
                boolean normalized = false;
                if (realSize == 5) {
                    realSize = GLX.GL_BGRA;
                    normalized = true;
                }
                if (dataType == DataType.Double) {
                    GLX.glVertexAttribLPointer(i, realSize, dataType.value(), strideBytes, pointer);
                } else {
                    GLX.glVertexAttribPointer(i, realSize, dataType.value(), normalized, strideBytes, pointer);
                }
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
