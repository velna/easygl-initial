package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.*;

import java.util.function.IntConsumer;

public class GlVertexArray extends AbstractBindable<BindTarget.Default<VertexArray>, VertexArray> implements VertexArray {

    private final VertexAttribute[] attributes = new VertexAttribute[GLX.glGetInteger(GLX.GL_MAX_VERTEX_ATTRIBS)];

    private int stride;

    GlVertexArray() {
        this(GLX.glGenVertexArrays());
    }

    GlVertexArray(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteVertexArrays);
    }

    @Override
    public VertexArray enableAttributes(Number... layouts) {
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
                attribute(i).enable().setPointer(realSize, dataType, realSize == 5, strideBytes, pointer);
            }
            pointer += Math.abs(realSize) * dataType.bytes();
        }
        this.stride = stride;
        return this;
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
    public void drawArray(DrawMode mode, Buffer vbo, int first) {
        assertBinding();
        GLX.glDrawArrays(mode.value(), first, (int) vbo.size() / stride);
        GLX.checkError();
    }

    @Override
    public void drawElements(DrawMode mode, Buffer ebo, int indices) {
        assertBinding();
        GLX.glDrawElements(mode.value(), (int) ebo.count(), ebo.dataType().value(), indices);
        GLX.checkError();
    }
}
