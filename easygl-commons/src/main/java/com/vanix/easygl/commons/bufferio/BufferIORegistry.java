package com.vanix.easygl.commons.bufferio;

import com.vanix.easygl.commons.collection.eclipse.*;
import com.vanix.easygl.commons.util.TypeReference;
import org.joml.*;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntFunction;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BufferIORegistry {
    private static final Map<Object, BufferIO<?>> REGISTRY = new ConcurrentHashMap<>();
    private static final Map<Class<?>, IntFunction<BufferIO>> COLLECTION_TYPES = new ConcurrentHashMap<>();

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
        register(Vector2f.class, new Vector2fBufferIO());
        register(Vector3i.class, new Vector3iBufferIO());
        register(Vector3f.class, new Vector3fBufferIO());
        register(Vector3d.class, new Vector3dBufferIO());
        register(Matrix4f.class, new Matrix4fBufferIO());
        register(Matrix4d.class, new Matrix4dBufferIO());

        registerCollection(ByteBufferArrayList.class, count -> new BufferListBufferIO(count, Byte.BYTES, c -> new ByteBufferArrayList(c, (byte) 0)));
        registerCollection(ShortBufferArrayList.class, count -> new BufferListBufferIO(count, Short.BYTES, c -> new ShortBufferArrayList(c, (short) 0)));
        registerCollection(IntBufferArrayList.class, count -> new BufferListBufferIO(count, Integer.BYTES, c -> new IntBufferArrayList(c, 0)));
        registerCollection(FloatBufferArrayList.class, count -> new BufferListBufferIO(count, Float.BYTES, c -> new FloatBufferArrayList(c, 0f)));
        registerCollection(LongBufferArrayList.class, count -> new BufferListBufferIO(count, Long.BYTES, c -> new LongBufferArrayList(c, 0L)));
        registerCollection(DoubleBufferArrayList.class, count -> new BufferListBufferIO(count, Double.BYTES, c -> new DoubleBufferArrayList(c, 0d)));
    }

    public static <T> void register(Class<T> type, BufferIO<T> bufferIO) {
        if (REGISTRY.containsKey(type)) {
            throw new IllegalArgumentException("Duplicated type: " + type);
        }
        REGISTRY.put(type, bufferIO);
    }

    public static <T> void registerCollection(Class<T> type, IntFunction<BufferIO> factory) {
        if (COLLECTION_TYPES.containsKey(type)) {
            throw new IllegalArgumentException("Duplicated type: " + type);
        }
        COLLECTION_TYPES.put(type, factory);
    }

    static <T> BufferIO<T> get(T bean) {
        Class<T> type = (Class<T>) bean.getClass();
        if (type.isArray()) {
            return getByType(type, Array.getLength(bean));
        } else if (bean instanceof List<?> list) {
            return getByType(type, list.size());
        } else if (bean instanceof BufferList bufferList) {
            return getByType(type, bufferList.size());
        } else {
            return getByType(type);
        }
    }

    static <T> BufferIO<T> getByType(Class<T> type, int... lengths) {
        if (lengths == null || lengths.length == 0) {
            return (BufferIO<T>) REGISTRY.computeIfAbsent(type, BufferIORegistry::createStruct);
        } else {
            return (BufferIO<T>) REGISTRY.computeIfAbsent(new RegistryKey(type, lengths), BufferIORegistry::createClass);
        }
    }

    static <T> BufferIO<T> getByType(TypeReference<T> typeReference, int... lengths) {
        var type = typeReference.getType();
        return (BufferIO<T>) REGISTRY.computeIfAbsent(new RegistryKey(type, lengths), BufferIORegistry::createReference);
    }

    private static <T> BufferIO<T> createStruct(Object key) {
        return new StructBufferIO<>((Class<T>) key);
    }

    private static <T> BufferIO<T> createClass(Object keyObject) {
        RegistryKey key = (RegistryKey) keyObject;
        return getByType((Class) key.type, 0, key.lengths);
    }

    private static <T> BufferIO<T> createReference(Object keyObject) {
        RegistryKey key = (RegistryKey) keyObject;
        if (key.type instanceof Class<?> clazz) {
            return getByType((Class<T>) clazz, key.lengths);
        } else {
            return getRawType(key.type, 0, key.lengths);
        }
    }

    private static <T> BufferIO<T> getRawType(Type type, int i, int[] lengths) {
        BufferIO bufferIO = null;
        if (type instanceof ParameterizedType parameterizedType) {
            if (parameterizedType.getRawType().equals(List.class)) {
                var paramType = parameterizedType.getActualTypeArguments()[0];
                if (paramType instanceof ParameterizedType innerParamType) {
                    bufferIO = getRawType(innerParamType, i + 1, lengths);
                } else if (paramType instanceof Class<?> clazz) {
                    bufferIO = getByType(clazz, i + 1, lengths);
                }
                bufferIO = new ListBufferIO(bufferIO, lengths[i]);
            }
        }
        if (bufferIO == null) {
            throw new BufferIOException("Unsupported type: " + type);
        }
        return bufferIO;
    }

    private static BufferIO getByType(Class type, int i, int[] lengths) {
        BufferIO bufferIO;
        IntFunction<BufferIO> factory;
        if (type.isArray()) {
            bufferIO = getByType(type.getComponentType(), i + 1, lengths);
        } else if ((factory = COLLECTION_TYPES.get(type)) != null) {
            bufferIO = factory.apply(lengths[i++]);
        } else {
            bufferIO = getByType(type);
        }
        if (i == lengths.length) {
            return bufferIO;
        } else if (i == lengths.length - 1) {
            return new ArrayBufferIO(bufferIO, type, lengths[i]);
        } else {
            throw new BufferIOException("Expect length of " + i + " but found " + lengths.length);
        }
    }

    private static <T> BufferIO<T> create(Class<T> type) {
        if (type.isArray() || Collection.class.isAssignableFrom(type)) {
            throw new BufferIOException("Can not create array/Collection type BufferIO without length/size info: " + type);
        }
        return new StructBufferIO<>(type);
    }

    record RegistryKey(Type type, int[] lengths) {

    }

}
