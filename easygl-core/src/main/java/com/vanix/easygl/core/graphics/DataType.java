package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.Buffer;

public enum DataType {
    Byte("BYTE", java.lang.Byte.BYTES),
    UnsignedByte("UNSIGNED_BYTE", java.lang.Byte.BYTES),
    Short("SHORT", java.lang.Short.BYTES),
    UnsignedShort("UNSIGNED_SHORT", java.lang.Short.BYTES),
    Int("INT", Integer.BYTES),
    UnsignedInt("UNSIGNED_INT", Integer.BYTES),
    Float("FLOAT", java.lang.Float.BYTES),
    UnsignedFlot("2_BYTES", java.lang.Float.BYTES),
    Bytes3("3_BYTES", 3),
    Bytes4("4_BYTES", 4),
    Double("DOUBLE", java.lang.Double.BYTES);
    private final int value;
    private final int bytes;

    DataType(String id, int bytes) {
        this.value = MetaSystem.Graphics.queryInt(id);
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
