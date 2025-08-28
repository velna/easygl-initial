package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DataType;
import com.vanix.easygl.core.graphics.VertexArray;
import com.vanix.easygl.core.graphics.VertexAttribute;
import org.joml.Vector4d;
import org.joml.Vector4f;
import org.joml.Vector4i;
import org.lwjgl.system.MemoryStack;

import java.nio.*;

public class GlVertexAttribute extends SimpleIntEnum implements VertexAttribute {
    private final VertexArray vao;

    protected GlVertexAttribute(int value, VertexArray vao) {
        super(value);
        this.vao = vao;
    }

    @Override
    public VertexAttribute then() {
        return this;
    }

    @Override
    public boolean isEnabled() {
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_ENABLED) == GLX.GL_TRUE;
    }

    @Override
    public VertexAttribute enable() {
        vao.assertBinding();
        GLX.glEnableVertexAttribArray(value);
        return this;
    }

    @Override
    public VertexAttribute disable() {
        vao.assertBinding();
        GLX.glDisableVertexAttribArray(value);
        return this;
    }

    @Override
    public VertexAttribute setPointer(int size, DataType dataType, boolean normalized, int stride, int offset) {
        vao.assertBinding();
        size = size == 5 ? GLX.GL_BGRA : size;
        if (dataType == DataType.Double) {
            GLX.glVertexAttribLPointer(value, size, dataType.value(), stride, offset);
        } else {
            GLX.glVertexAttribPointer(value, size, dataType.value(), normalized, stride, offset);
        }
        return this;
    }

    @Override
    public VertexAttribute setFormat(int size, DataType dataType, boolean normalized, int offset) {
        vao.assertBinding();
        GLX.glVertexAttribFormat(value, size, dataType.value(), normalized, offset);
        return this;
    }

    @Override
    public VertexAttribute setDivisor(int divisor) {
        vao.assertBinding();
        GLX.glVertexAttribDivisor(value, divisor);
        return this;
    }

    @Override
    public VertexAttribute bind(Buffer.BindingPoint bindingPoint) {
        vao.assertBinding();
        GLX.glVertexAttribBinding(value, bindingPoint.value());
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0) {
        vao.assertBinding();
        GLX.glVertexAttrib1s(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0, short v1) {
        vao.assertBinding();
        GLX.glVertexAttrib2s(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0, short v1, short v2) {
        vao.assertBinding();
        GLX.glVertexAttrib3s(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0, short v1, short v2, short v3) {
        vao.assertBinding();
        GLX.glVertexAttrib4s(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setShort(short[] array) {
        vao.assertBinding();
        switch (array.length) {
            case 1 -> GLX.glVertexAttrib1sv(value, array);
            case 2 -> GLX.glVertexAttrib2sv(value, array);
            case 3 -> GLX.glVertexAttrib3sv(value, array);
            case 4 -> GLX.glVertexAttrib4sv(value, array);
            default -> throw new IllegalArgumentException("Invalid array length: " + array.length);
        }
        return this;
    }

    @Override
    public VertexAttribute setShort(ShortBuffer buffer) {
        vao.assertBinding();
        switch (buffer.remaining()) {
            case 1 -> GLX.glVertexAttrib1sv(value, buffer);
            case 2 -> GLX.glVertexAttrib2sv(value, buffer);
            case 3 -> GLX.glVertexAttrib3sv(value, buffer);
            case 4 -> GLX.glVertexAttrib4sv(value, buffer);
            default -> throw new IllegalArgumentException("Invalid buffer remaining: " + buffer.remaining());
        }
        return this;
    }

    @Override
    public VertexAttribute setUnsignedShort(short[] array) {
        vao.assertBinding();
        GLX.glVertexAttrib4usv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setUnsignedShort(ShortBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4usv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setInt(int[] array) {
        vao.assertBinding();
        GLX.glVertexAttrib4iv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setInt(IntBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4iv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setUnsignedInt(int[] array) {
        vao.assertBinding();
        GLX.glVertexAttrib4uiv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setUnsignedInt(IntBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4uiv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0) {
        vao.assertBinding();
        GLX.glVertexAttrib1f(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0, float v1) {
        vao.assertBinding();
        GLX.glVertexAttrib2f(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0, float v1, float v2) {
        vao.assertBinding();
        GLX.glVertexAttrib3f(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0, float v1, float v2, float v3) {
        vao.assertBinding();
        GLX.glVertexAttrib4f(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float[] array) {
        vao.assertBinding();
        switch (array.length) {
            case 1 -> GLX.glVertexAttrib1fv(value, array);
            case 2 -> GLX.glVertexAttrib2fv(value, array);
            case 3 -> GLX.glVertexAttrib3fv(value, array);
            case 4 -> GLX.glVertexAttrib4fv(value, array);
            default -> throw new IllegalArgumentException("Invalid array length: " + array.length);
        }
        return this;
    }

    @Override
    public VertexAttribute setFloat(FloatBuffer buffer) {
        vao.assertBinding();
        switch (buffer.remaining()) {
            case 1 -> GLX.glVertexAttrib1fv(value, buffer);
            case 2 -> GLX.glVertexAttrib2fv(value, buffer);
            case 3 -> GLX.glVertexAttrib3fv(value, buffer);
            case 4 -> GLX.glVertexAttrib4fv(value, buffer);
            default -> throw new IllegalArgumentException("Invalid buffer remaining: " + buffer.remaining());
        }
        return this;
    }

    @Override
    public VertexAttribute setDouble(double v0) {
        vao.assertBinding();
        GLX.glVertexAttrib1d(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double v0, double v1) {
        vao.assertBinding();
        GLX.glVertexAttrib2d(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double v0, double v1, double v2) {
        vao.assertBinding();
        GLX.glVertexAttrib3d(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double v0, double v1, double v2, double v3) {
        vao.assertBinding();
        GLX.glVertexAttrib4d(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double[] array) {
        vao.assertBinding();
        switch (array.length) {
            case 1 -> GLX.glVertexAttrib1dv(value, array);
            case 2 -> GLX.glVertexAttrib2dv(value, array);
            case 3 -> GLX.glVertexAttrib3dv(value, array);
            case 4 -> GLX.glVertexAttrib4dv(value, array);
            default -> throw new IllegalArgumentException("Invalid array length: " + array.length);
        }
        return this;
    }

    @Override
    public VertexAttribute setDouble(DoubleBuffer buffer) {
        vao.assertBinding();
        switch (buffer.remaining()) {
            case 1 -> GLX.glVertexAttrib1dv(value, buffer);
            case 2 -> GLX.glVertexAttrib2dv(value, buffer);
            case 3 -> GLX.glVertexAttrib3dv(value, buffer);
            case 4 -> GLX.glVertexAttrib4dv(value, buffer);
            default -> throw new IllegalArgumentException("Invalid buffer remaining: " + buffer.remaining());
        }
        return this;
    }

    @Override
    public VertexAttribute setNormalizedByte(ByteBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nbv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedByte(byte v0, byte v1, byte v2, byte v3) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nub(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedByte(ByteBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nubv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedShort4(short[] array) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nsv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedShort4(ShortBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nsv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedShort4(short[] array) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nusv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedShort4(ShortBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nusv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedInt4(int[] array) {
        vao.assertBinding();
        GLX.glVertexAttrib4Niv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedInt4(IntBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4Niv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedInt4(int[] array) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nuiv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedInt4(IntBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttrib4Nuiv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0) {
        vao.assertBinding();
        GLX.glVertexAttribI1i(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0, int v1) {
        vao.assertBinding();
        GLX.glVertexAttribI2i(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0, int v1, int v2) {
        vao.assertBinding();
        GLX.glVertexAttribI3i(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0, int v1, int v2, int v3) {
        vao.assertBinding();
        GLX.glVertexAttribI4i(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int[] array) {
        vao.assertBinding();
        switch (array.length) {
            case 1 -> GLX.glVertexAttribI1iv(value, array);
            case 2 -> GLX.glVertexAttribI2iv(value, array);
            case 3 -> GLX.glVertexAttribI3iv(value, array);
            case 4 -> GLX.glVertexAttribI4iv(value, array);
            default -> throw new IllegalArgumentException("Invalid array length: " + array.length);
        }
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(IntBuffer buffer) {
        vao.assertBinding();
        switch (buffer.remaining()) {
            case 1 -> GLX.glVertexAttribI1iv(value, buffer);
            case 2 -> GLX.glVertexAttribI2iv(value, buffer);
            case 3 -> GLX.glVertexAttribI3iv(value, buffer);
            case 4 -> GLX.glVertexAttribI4iv(value, buffer);
            default -> throw new IllegalArgumentException("Invalid buffer remaining: " + buffer.remaining());
        }
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int v0) {
        vao.assertBinding();
        GLX.glVertexAttribI1ui(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int v0, int v1) {
        vao.assertBinding();
        GLX.glVertexAttribI2ui(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int v0, int v1, int v2) {
        vao.assertBinding();
        GLX.glVertexAttribI3ui(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int v0, int v1, int v2, int v3) {
        vao.assertBinding();
        GLX.glVertexAttribI4ui(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int[] array) {
        vao.assertBinding();
        switch (array.length) {
            case 1 -> GLX.glVertexAttribI1uiv(value, array);
            case 2 -> GLX.glVertexAttribI2uiv(value, array);
            case 3 -> GLX.glVertexAttribI3uiv(value, array);
            case 4 -> GLX.glVertexAttribI4uiv(value, array);
            default -> throw new IllegalArgumentException("Invalid array length: " + array.length);
        }
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(IntBuffer buffer) {
        vao.assertBinding();
        switch (buffer.remaining()) {
            case 1 -> GLX.glVertexAttribI1uiv(value, buffer);
            case 2 -> GLX.glVertexAttribI2uiv(value, buffer);
            case 3 -> GLX.glVertexAttribI3uiv(value, buffer);
            case 4 -> GLX.glVertexAttribI4uiv(value, buffer);
            default -> throw new IllegalArgumentException("Invalid buffer remaining: " + buffer.remaining());
        }
        return this;
    }

    @Override
    public VertexAttribute setExtendedByte4(ByteBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttribI4bv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedByte4(ByteBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttribI4ubv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedShort4(short[] array) {
        vao.assertBinding();
        GLX.glVertexAttribI4sv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setExtendedShort4(ShortBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttribI4sv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedShort4(short[] array) {
        vao.assertBinding();
        GLX.glVertexAttribI4usv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedShort4(ShortBuffer buffer) {
        vao.assertBinding();
        GLX.glVertexAttribI4usv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0) {
        vao.assertBinding();
        GLX.glVertexAttribL1d(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0, double v1) {
        vao.assertBinding();
        GLX.glVertexAttribL2d(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0, double v1, double v2) {
        vao.assertBinding();
        GLX.glVertexAttribL3d(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0, double v1, double v2, double v3) {
        vao.assertBinding();
        GLX.glVertexAttribL4d(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double[] array) {
        vao.assertBinding();
        switch (array.length) {
            case 1 -> GLX.glVertexAttribL1dv(value, array);
            case 2 -> GLX.glVertexAttribL2dv(value, array);
            case 3 -> GLX.glVertexAttribL3dv(value, array);
            case 4 -> GLX.glVertexAttribL4dv(value, array);
            default -> throw new IllegalArgumentException("Invalid array length: " + array.length);
        }
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(DoubleBuffer buffer) {
        vao.assertBinding();
        switch (buffer.remaining()) {
            case 1 -> GLX.glVertexAttribL1dv(value, buffer);
            case 2 -> GLX.glVertexAttribL2dv(value, buffer);
            case 3 -> GLX.glVertexAttribL3dv(value, buffer);
            case 4 -> GLX.glVertexAttribL4dv(value, buffer);
            default -> throw new IllegalArgumentException("Invalid buffer remaining: " + buffer.remaining());
        }
        return this;
    }

    @Override
    public VertexAttribute setPackedInt1(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP1ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedInt2(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP2ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedInt3(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP3ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedInt4(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP4ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt1(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP1ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt2(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP2ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt3(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP3ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt4(boolean normalized, int v) {
        vao.assertBinding();
        GLX.glVertexAttribP4ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat1(int v) {
        vao.assertBinding();
        GLX.glVertexAttribP1ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat2(int v) {
        vao.assertBinding();
        GLX.glVertexAttribP2ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat3(int v) {
        vao.assertBinding();
        GLX.glVertexAttribP3ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat4(int v) {
        vao.assertBinding();
        GLX.glVertexAttribP4ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }

    @Override
    public Buffer getBindingBuffer() {
        vao.assertBinding();
        int buffer = GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING);
        return buffer == 0 ? null : GlBuffer.get(buffer);
    }

    @Override
    public int getSize() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_SIZE);
    }

    @Override
    public int getStride() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_STRIDE);
    }

    @Override
    public DataType getDataType() {
        vao.assertBinding();
        return Cache.DataType.get(GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_TYPE));
    }

    @Override
    public boolean isNormalized() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_NORMALIZED) == GLX.GL_TRUE;
    }

    @Override
    public boolean isInteger() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_INTEGER) == GLX.GL_TRUE;
    }

    @Override
    public boolean isLong() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_LONG) == GLX.GL_TRUE;
    }

    @Override
    public int getDivisor() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_ARRAY_DIVISOR);
    }

    @Override
    public int getBindingPoint() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_BINDING);
    }

    @Override
    public int getOffset() {
        vao.assertBinding();
        return GLX.glGetVertexAttribi(value, GLX.GL_VERTEX_ATTRIB_RELATIVE_OFFSET);
    }

    @Override
    public Vector4f getFloat() {
        vao.assertBinding();
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var buffer = stack.mallocFloat(4);
            GLX.glGetVertexAttribfv(value, GLX.GL_CURRENT_VERTEX_ATTRIB, buffer);
            return new Vector4f(buffer);
        }
    }

    @Override
    public Vector4d getFloatAsDouble() {
        vao.assertBinding();
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var buffer = stack.mallocDouble(4);
            GLX.glGetVertexAttribdv(value, GLX.GL_CURRENT_VERTEX_ATTRIB, buffer);
            return new Vector4d(buffer);
        }
    }

    @Override
    public Vector4i getFloatAsInt() {
        vao.assertBinding();
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var buffer = stack.mallocInt(4);
            GLX.glGetVertexAttribiv(value, GLX.GL_CURRENT_VERTEX_ATTRIB, buffer);
            return new Vector4i(buffer);
        }
    }

    @Override
    public Vector4i getInt() {
        vao.assertBinding();
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var buffer = stack.mallocInt(4);
            GLX.glGetVertexAttribIiv(value, GLX.GL_CURRENT_VERTEX_ATTRIB, buffer);
            return new Vector4i(buffer);
        }
    }

    @Override
    public Vector4i getUnsignedInt() {
        vao.assertBinding();
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var buffer = stack.mallocInt(4);
            GLX.glGetVertexAttribIuiv(value, GLX.GL_CURRENT_VERTEX_ATTRIB, buffer);
            return new Vector4i(buffer);
        }
    }

    @Override
    public Vector4d getDouble() {
        vao.assertBinding();
        try (MemoryStack stack = MemoryStack.stackGet()) {
            var buffer = stack.mallocDouble(4);
            GLX.glGetVertexAttribLdv(value, GLX.GL_CURRENT_VERTEX_ATTRIB, buffer);
            return new Vector4d(buffer);
        }
    }
}
