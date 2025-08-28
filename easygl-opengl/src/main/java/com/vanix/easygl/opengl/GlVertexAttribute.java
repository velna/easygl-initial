package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DataType;
import com.vanix.easygl.core.graphics.VertexAttribute;

import java.nio.*;

public class GlVertexAttribute extends SimpleIntEnum implements VertexAttribute {

    private static final VertexAttribute[] ATTRIBUTES = new VertexAttribute[GLX.glGetInteger(GLX.GL_MAX_VERTEX_ATTRIBS)];

    private GlVertexAttribute(int value) {
        super(value);
    }

    static VertexAttribute of(int i) {
        var ret = ATTRIBUTES[i];
        if (ret == null) {
            ret = new GlVertexAttribute(i);
            ATTRIBUTES[i] = ret;
        }
        return ret;
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
        GLX.glEnableVertexAttribArray(value);
        return this;
    }

    @Override
    public VertexAttribute disable() {
        GLX.glDisableVertexAttribArray(value);
        return this;
    }

    @Override
    public VertexAttribute setPointer(int size, DataType dataType, boolean normalized, int stride, int offset) {
        GLX.glVertexAttribPointer(value, size, dataType.value(), normalized, stride, offset);
        return this;
    }

    @Override
    public VertexAttribute setFormat(int size, DataType dataType, boolean normalized, int offset) {
        GLX.glVertexAttribFormat(value, size, dataType.value(), normalized, offset);
        return this;
    }

    @Override
    public VertexAttribute setDivisor(int divisor) {
        GLX.glVertexAttribDivisor(value, divisor);
        return this;
    }

    @Override
    public VertexAttribute bind(Buffer.BindingPoint bindingPoint) {
        GLX.glVertexAttribBinding(value, bindingPoint.value());
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0) {
        GLX.glVertexAttrib1s(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0, short v1) {
        GLX.glVertexAttrib2s(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0, short v1, short v2) {
        GLX.glVertexAttrib3s(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setShort(short v0, short v1, short v2, short v3) {
        GLX.glVertexAttrib4s(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setShort(short[] array) {
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
        GLX.glVertexAttrib4usv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setUnsignedShort(ShortBuffer buffer) {
        GLX.glVertexAttrib4usv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setInt(int[] array) {
        GLX.glVertexAttrib4iv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setInt(IntBuffer buffer) {
        GLX.glVertexAttrib4iv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setUnsignedInt(int[] array) {
        GLX.glVertexAttrib4uiv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setUnsignedInt(IntBuffer buffer) {
        GLX.glVertexAttrib4uiv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0) {
        GLX.glVertexAttrib1f(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0, float v1) {
        GLX.glVertexAttrib2f(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0, float v1, float v2) {
        GLX.glVertexAttrib3f(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float v0, float v1, float v2, float v3) {
        GLX.glVertexAttrib4f(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setFloat(float[] array) {
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
        GLX.glVertexAttrib1d(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double v0, double v1) {
        GLX.glVertexAttrib2d(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double v0, double v1, double v2) {
        GLX.glVertexAttrib3d(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double v0, double v1, double v2, double v3) {
        GLX.glVertexAttrib4d(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setDouble(double[] array) {
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
        GLX.glVertexAttrib4Nbv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedByte(byte v0, byte v1, byte v2, byte v3) {
        GLX.glVertexAttrib4Nub(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedByte(ByteBuffer buffer) {
        GLX.glVertexAttrib4Nubv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedShort4(short[] array) {
        GLX.glVertexAttrib4Nsv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedShort4(ShortBuffer buffer) {
        GLX.glVertexAttrib4Nsv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedShort4(short[] array) {
        GLX.glVertexAttrib4Nusv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedShort4(ShortBuffer buffer) {
        GLX.glVertexAttrib4Nusv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedInt4(int[] array) {
        GLX.glVertexAttrib4Niv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedInt4(IntBuffer buffer) {
        GLX.glVertexAttrib4Niv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedInt4(int[] array) {
        GLX.glVertexAttrib4Nuiv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setNormalizedUnsignedInt4(IntBuffer buffer) {
        GLX.glVertexAttrib4Nuiv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0) {
        GLX.glVertexAttribI1i(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0, int v1) {
        GLX.glVertexAttribI2i(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0, int v1, int v2) {
        GLX.glVertexAttribI3i(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int v0, int v1, int v2, int v3) {
        GLX.glVertexAttribI4i(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setExtendedInt(int[] array) {
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
        GLX.glVertexAttribI1ui(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int v0, int v1) {
        GLX.glVertexAttribI2ui(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int v0, int v1, int v2) {
        GLX.glVertexAttribI3ui(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int v0, int v1, int v2, int v3) {
        GLX.glVertexAttribI4ui(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedInt(int[] array) {
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
        GLX.glVertexAttribI4bv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedByte4(ByteBuffer buffer) {
        GLX.glVertexAttribI4ubv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedShort4(short[] array) {
        GLX.glVertexAttribI4sv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setExtendedShort4(ShortBuffer buffer) {
        GLX.glVertexAttribI4sv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedShort4(short[] array) {
        GLX.glVertexAttribI4usv(value, array);
        return this;
    }

    @Override
    public VertexAttribute setExtendedUnsignedShort4(ShortBuffer buffer) {
        GLX.glVertexAttribI4usv(value, buffer);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0) {
        GLX.glVertexAttribL1d(value, v0);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0, double v1) {
        GLX.glVertexAttribL2d(value, v0, v1);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0, double v1, double v2) {
        GLX.glVertexAttribL3d(value, v0, v1, v2);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double v0, double v1, double v2, double v3) {
        GLX.glVertexAttribL4d(value, v0, v1, v2, v3);
        return this;
    }

    @Override
    public VertexAttribute setDirectDouble(double[] array) {
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
        GLX.glVertexAttribP1ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedInt2(boolean normalized, int v) {
        GLX.glVertexAttribP2ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedInt3(boolean normalized, int v) {
        GLX.glVertexAttribP3ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedInt4(boolean normalized, int v) {
        GLX.glVertexAttribP4ui(value, GLX.GL_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt1(boolean normalized, int v) {
        GLX.glVertexAttribP1ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt2(boolean normalized, int v) {
        GLX.glVertexAttribP2ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt3(boolean normalized, int v) {
        GLX.glVertexAttribP3ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedUnsignedInt4(boolean normalized, int v) {
        GLX.glVertexAttribP4ui(value, GLX.GL_UNSIGNED_INT_2_10_10_10_REV, normalized, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat1(int v) {
        GLX.glVertexAttribP1ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat2(int v) {
        GLX.glVertexAttribP2ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat3(int v) {
        GLX.glVertexAttribP3ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }

    @Override
    public VertexAttribute setPackedFloat4(int v) {
        GLX.glVertexAttribP4ui(value, GLX.GL_UNSIGNED_INT_10F_11F_11F_REV, false, v);
        return this;
    }
}
