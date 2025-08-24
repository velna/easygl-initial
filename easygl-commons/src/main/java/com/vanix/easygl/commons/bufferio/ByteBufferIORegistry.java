package com.vanix.easygl.commons.bufferio;

import org.joml.*;

import java.util.HashMap;
import java.util.Map;

public class ByteBufferIORegistry {
    private static final Map<Class<?>, ByteBufferIO<?>> REGISTRY = new HashMap<>();

    static {
        register(byte.class, new ByteByteBufferIO());
        register(short.class, new ShortByteBufferIO());
        register(int.class, new IntByteBufferIO());
        register(float.class, new FloatByteBufferIO());
        register(long.class, new LongByteBufferIO());
        register(double.class, new DoubleByteBufferIO());
        register(byte[].class, new ByteArrayByteBufferIO());
        register(short[].class, new ShortArrayByteBufferIO());
        register(int[].class, new IntArrayByteBufferIO());
        register(float[].class, new FloatArrayByteBufferIO());
        register(long[].class, new LongArrayByteBufferIO());
        register(double[].class, new DoubleArrayByteBufferIO());
        register(Vector3i.class, new Vector3iByteBufferIO());
        register(Vector3f.class, new Vector3fByteBufferIO());
        register(Vector3d.class, new Vector3dByteBufferIO());
        register(Matrix4f.class, new Matrix4fByteBufferIO());
        register(Matrix4d.class, new Matrix4dByteBufferIO());
    }

    public static <T> void register(Class<T> type, ByteBufferIO<T> byteBufferIO) {
        if (REGISTRY.containsKey(type)) {
            throw new IllegalArgumentException("Duplicated type: " + type);
        }
        REGISTRY.put(type, byteBufferIO);
    }

    @SuppressWarnings("unchecked")
    public static <T> ByteBufferIO<T> get(Class<T> type) {
        return (ByteBufferIO<T>) REGISTRY.get(type);
    }
}
