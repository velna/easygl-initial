package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;

import java.nio.Buffer;

public enum DataType {
    Byte(GLC.GL_BYTE, java.lang.Byte.BYTES),
    UnsignedByte(GLC.GL_UNSIGNED_BYTE, java.lang.Byte.BYTES),
    Short(GLC.GL_SHORT, java.lang.Short.BYTES),
    UnsignedShort(GLC.GL_UNSIGNED_SHORT, java.lang.Short.BYTES),
    Int(GLC.GL_INT, Integer.BYTES),
    UnsignedInt(GLC.GL_UNSIGNED_INT, Integer.BYTES),
    Float(GLC.GL_FLOAT, java.lang.Float.BYTES),
    UnsignedFlot(GLC.GL_2_BYTES, java.lang.Float.BYTES),
    Bytes3(GLC.GL_3_BYTES, 3),
    Bytes4(GLC.GL_4_BYTES, 4),
    Double(GLC.GL_DOUBLE, java.lang.Double.BYTES);
    private final int value;
    private final int bytes;

    DataType(int value, int bytes) {
        this.value = value;
        this.bytes = bytes;
    }

    public int value() {
        return value;
    }

    public int bytes() {
        return bytes;
    }

    public int bytesOfCount(int count) {
        return bytes * count;
    }

    public static int bytesOf(byte[] data) {
        return data.length;
    }

    public static int bytesOf(short[] data) {
        return Short.bytesOfCount(data.length);
    }

    public static int bytesOf(int[] data) {
        return Int.bytesOfCount(data.length);
    }

    public static int bytesOf(float[] data) {
        return Float.bytesOfCount(data.length);
    }

    public static int bytesOf(double[] data) {
        return Double.bytesOfCount(data.length);
    }

    public static int bytesOf(Buffer data) {
        return Short.bytesOfCount(data.remaining());
    }

}
