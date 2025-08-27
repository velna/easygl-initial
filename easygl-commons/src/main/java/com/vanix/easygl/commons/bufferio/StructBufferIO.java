package com.vanix.easygl.commons.bufferio;

import org.apache.commons.beanutils2.PropertyUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StructBufferIO<T> implements BufferIO<T> {
    private final Constructor<T> defaultConstructor;
    private final Map<String, BindingMeta> metaMap;
    private final int size;

    @SuppressWarnings("unchecked")
    public StructBufferIO(Class<T> type) {
        Constructor<T> structConstructor = null;
        Constructor<T> defaultConstructor = null;
        for (var constructor : type.getConstructors()) {
            if (constructor.isAnnotationPresent(BufferStruct.class)) {
                if (structConstructor != null) {
                    throw new BufferIOException("Multiple BufferStruct constructor found in class: " + type);
                }
                structConstructor = (Constructor<T>) constructor;
            } else if (constructor.getParameters().length == 0) {
                defaultConstructor = (Constructor<T>) constructor;
            }
        }
        if (structConstructor == null) {
            throw new BufferIOException("No BufferStruct constructor found in class: " + type);
        }
        if (defaultConstructor == null) {
            throw new BufferIOException("No default constructor found in class: " + type);
        }
        this.defaultConstructor = defaultConstructor;
        this.metaMap = new LinkedHashMap<>();
        int size = 0;
        var descriptors = PropertyUtils.getPropertyDescriptors(structConstructor.getDeclaringClass());
        for (var param : structConstructor.getParameters()) {
            var paramType = (Class<Object>) param.getType();
            var bufferField = param.getAnnotation(BufferField.class);
            int count;
            BufferIO<Object> bufferIo;
            if (bufferField == null || bufferField.count() <= 0) {
                count = 1;
                bufferIo = BufferIO.ofType(paramType);
            } else {
                count = bufferField.count();
                bufferIo = BufferIO.ofType(paramType, count);
            }
            int dataSize = bufferIo.size();
            String fieldName = bufferField != null && !bufferField.name().isEmpty() ? bufferField.name() : param.getName();
            var descriptor = findDescriptor(descriptors, fieldName, paramType);
            if (descriptor == null) {
                throw new BufferIOException("Can not find property of constructor param " + param + " for class " + structConstructor.getDeclaringClass());
            }
            metaMap.put(fieldName, new StructBufferIO.BindingMeta(size, dataSize, count, new StructBufferIO.PropertyIO(descriptor), bufferIo));
            size += dataSize;
        }
        this.size = size;
    }

    private StructBufferIO(Constructor<T> defaultConstructor, Map<String, BindingMeta> metaMap, int size) {
        this.defaultConstructor = defaultConstructor;
        this.metaMap = metaMap;
        this.size = size;
    }

    private PropertyDescriptor findDescriptor(PropertyDescriptor[] descriptors, String name, Class<?> type) {
        for (var descriptor : descriptors) {
            if (descriptor.getName().equals(name) && descriptor.getPropertyType().equals(type)) {
                return descriptor;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void write(@Nonnull T object, ByteBuffer buffer) {
        for (var meta : metaMap.values()) {
            meta.write(object, buffer);
        }
    }

    @Override
    public void read(@Nullable T object, ByteBuffer buffer, Consumer<T> setter) {
        boolean setFlag = false;
        if (object == null) {
            try {
                object = defaultConstructor.newInstance();
                setFlag = true;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new BufferIOException("Error invoke default construct: ", e);
            }
        }
        for (var meta : metaMap.values()) {
            meta.read(object, buffer, null);
        }
        if (setFlag) {
            setter.accept(object);
        }
    }

    public FieldBufferIO<Object> getFieldBufferIO(String fieldName) {
        var meta = metaMap.get(fieldName);
        if (meta == null) {
            throw new BufferIOException("Can not find field of field " + fieldName + " within type " + defaultConstructor.getDeclaringClass());
        }
        return meta;
    }

    public static class Builder<T> {
        private final StructBufferIO<T> origin;
        private final int size;
        private final Map<String, BindingMeta> metaMap = new LinkedHashMap<>();

        public Builder(StructBufferIO<T> origin, int size) {
            this.origin = origin;
            this.size = size;
        }

        public Builder<T> withField(String name, int offset, int dataSize) {
            var meta = origin.metaMap.get(name);
            if (meta == null) {
                throw new BufferIOException("Can not find field of field " + name + " within type " + origin.defaultConstructor.getDeclaringClass());
            }
            if (dataSize < meta.dataSize) {
                throw new BufferIOException(String.format("Incompatible data size of field %s within type %s: origin=%d, expect=%d",
                        name, origin.defaultConstructor.getDeclaringClass(), meta.dataSize, dataSize));
            }
            if (offset + dataSize > size) {
                throw new BufferIOException(String.format("Offset %d with data size %d is larger than current data size %d of field %s within type %s.",
                        offset, dataSize, size, name, origin.defaultConstructor.getDeclaringClass()));
            }
            metaMap.put(name, new BindingMeta(offset, dataSize, meta.count, meta.propertyIO, meta.bufferIO));
            return this;
        }

        public StructBufferIO<T> build() {
            return new StructBufferIO<>(origin.defaultConstructor, metaMap, size);
        }
    }


    record PropertyIO(PropertyDescriptor propertyDescriptor) {

        Object getProperty(Object bean) {
            try {
                return propertyDescriptor.getReadMethod().invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BufferIOException("Error get property of " + propertyDescriptor.getName() + " on bean " + bean.getClass(), e);
            }
        }

        void setProperty(Object bean, Object value) {
            try {
                propertyDescriptor.getWriteMethod().invoke(bean, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BufferIOException("Error set property of " + propertyDescriptor.getName() + " on bean " + bean.getClass(), e);
            }
        }
    }

    private record BindingMeta(int offset, int dataSize, int count,
                               PropertyIO propertyIO,
                               BufferIO<Object> bufferIO) implements FieldBufferIO<Object> {
        @Override
        public int size() {
            return dataSize;
        }

        @Override
        public void write(@Nonnull Object bean, ByteBuffer storage) {
            Object value = propertyIO.getProperty(bean);
            if (value != null) {
                bufferIO.write(value, storage);
            } else {
                storage.position(storage.position() + bufferIO.size());
            }
        }

        @Override
        public void read(@Nullable Object bean, ByteBuffer storage, Consumer<Object> setter) {
            Object value = propertyIO.getProperty(bean);
            bufferIO.read(value, storage, v -> propertyIO.setProperty(bean, v));
        }

        @Override
        public String getName() {
            return propertyIO.propertyDescriptor.getName();
        }

        @Override
        public int getOffset() {
            return offset;
        }

        @Override
        public int getDataSize() {
            return dataSize;
        }
    }
}
