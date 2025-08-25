package com.vanix.easygl.commons.bufferio;

import org.joml.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BufferIORegistry {
    private static final Map<Class<?>, BufferIO<?>> REGISTRY = new ConcurrentHashMap<>();

    static {
        register(byte.class, new ByteBufferIO());
        register(short.class, new ShortBufferIO());
        register(int.class, new IntBufferIO());
        register(float.class, new FloatBufferIO());
        register(long.class, new LongBufferIO());
        register(double.class, new DoubleBufferIO());
        register(byte[].class, new ByteArrayBufferIO());
        register(short[].class, new ShortArrayBufferIO());
        register(int[].class, new IntArrayBufferIO());
        register(float[].class, new FloatArrayBufferIO());
        register(long[].class, new LongArrayBufferIO());
        register(double[].class, new DoubleArrayBufferIO());
        register(Vector3i.class, new Vector3IBufferIO());
        register(Vector3f.class, new Vector3FBufferIO());
        register(Vector3d.class, new Vector3DBufferIO());
        register(Matrix4f.class, new Matrix4FBufferIO());
        register(Matrix4d.class, new Matrix4DBufferIO());
    }

    public static <T> void register(Class<T> type, BufferIO<T> bufferIO) {
        if (REGISTRY.containsKey(type)) {
            throw new IllegalArgumentException("Duplicated type: " + type);
        }
        REGISTRY.put(type, bufferIO);
    }

    @SuppressWarnings("unchecked")
    public static <T> BufferIO<T> get(Class<T> type) {
        return (BufferIO<T>) REGISTRY.computeIfAbsent(type, StructBufferIO::new);
    }

}
