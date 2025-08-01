package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.Buffer;

public enum DataType {
    Byte(MetaSystem.Graphics.queryInt("BYTE"), java.lang.Byte.BYTES),
    UnsignedByte(MetaSystem.Graphics.queryInt("UNSIGNED_BYTE"), java.lang.Byte.BYTES),
    Short(MetaSystem.Graphics.queryInt("SHORT"), java.lang.Short.BYTES),
    UnsignedShort(MetaSystem.Graphics.queryInt("UNSIGNED_SHORT"), java.lang.Short.BYTES),
    Int(MetaSystem.Graphics.queryInt("INT"), Integer.BYTES),
    UnsignedInt(MetaSystem.Graphics.queryInt("UNSIGNED_INT"), Integer.BYTES),
    Float(MetaSystem.Graphics.queryInt("FLOAT"), java.lang.Float.BYTES),
    UnsignedFlot(MetaSystem.Graphics.queryInt("2_BYTES"), java.lang.Float.BYTES),
    Bytes3(MetaSystem.Graphics.queryInt("3_BYTES"), 3),
    Bytes4(MetaSystem.Graphics.queryInt("4_BYTES"), 4),
    Double(MetaSystem.Graphics.queryInt("DOUBLE"), java.lang.Double.BYTES);
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
