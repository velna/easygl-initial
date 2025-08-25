package com.vanix.easygl.commons.bufferio;

import com.vanix.easygl.commons.collection.eclipse.*;
import com.vanix.easygl.commons.util.TypeReference;
import org.joml.*;

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
        register(Vector3i.class, new Vector3IBufferIO());
        register(Vector3f.class, new Vector3FBufferIO());
        register(Vector3d.class, new Vector3DBufferIO());
        register(Matrix4f.class, new Matrix4FBufferIO());
        register(Matrix4d.class, new Matrix4DBufferIO());

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

    static <T> BufferIO<T> get(Class<T> type, int... lengths) {
        if (lengths == null || lengths.length == 0) {
            return (BufferIO<T>) REGISTRY.computeIfAbsent(type, BufferIORegistry::createStruct);
        } else {
            return (BufferIO<T>) REGISTRY.computeIfAbsent(new RegistryKey(type, lengths), BufferIORegistry::createClass);
        }
    }

    static <T> BufferIO<T> get(TypeReference<T> typeReference, int... lengths) {
        var type = typeReference.getType();
        return (BufferIO<T>) REGISTRY.computeIfAbsent(new RegistryKey(type, lengths), BufferIORegistry::createReference);
    }

    private static <T> BufferIO<T> createStruct(Object key) {
        return new StructBufferIO<>((Class<T>) key);
    }

    private static <T> BufferIO<T> createClass(Object keyObject) {
        RegistryKey key = (RegistryKey) keyObject;
        return get((Class) key.type, 0, key.lengths);
    }

    private static <T> BufferIO<T> createReference(Object keyObject) {
        RegistryKey key = (RegistryKey) keyObject;
        if (key.type instanceof Class<?> clazz) {
            return get((Class<T>) clazz, key.lengths);
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
                    bufferIO = get(clazz, i + 1, lengths);
                }
                bufferIO = new ListBufferIO(bufferIO, lengths[i]);
            }
        }
        if (bufferIO == null) {
            throw new BufferIOException("Unsupported type: " + type);
        }
        return bufferIO;
    }

    private static BufferIO get(Class type, int i, int[] lengths) {
        BufferIO bufferIO;
        IntFunction<BufferIO> factory;
        if (type.isArray()) {
            bufferIO = get(type.getComponentType(), i + 1, lengths);
        } else if ((factory = COLLECTION_TYPES.get(type)) != null) {
            bufferIO = factory.apply(lengths[i++]);
        } else {
            bufferIO = get(type);
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

    public record CollectionFactory(IntFunction<BufferIO> factory, int sizeOfUnit) {
    }
}
