package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.*;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.*;
import java.util.function.Consumer;

public interface Buffer extends Handle, Bindable<Buffer.Type, Buffer> {

    BindableMeta<Type, Buffer> Meta = MetaSystem.Graphics.of(Buffer.class);

    enum Type implements BindTarget<Type, Buffer> {
        //        Array(EasyGL.queryInt("ARRAY_BUFFER),
        Array("ARRAY_BUFFER"),
        CopyRead("COPY_READ_BUFFER"),
        CopyWrite("COPY_WRITE_BUFFER"),
        ElementArray("ELEMENT_ARRAY_BUFFER"),
        PixelPack("PIXEL_PACK_BUFFER"),
        PixelUnpack("PIXEL_UNPACK_BUFFER"),
        Texture("TEXTURE_BUFFER"),
        TransformFeedback("TRANSFORM_FEEDBACK_BUFFER"),
        Uniform("UNIFORM_BUFFER");

        final int value;

        private final BindingState<Type, Buffer> state;

        Type(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
            state = Meta.newBindingState(name());
        }

        @Override
        public int value() {
            return value;
        }

        @Override
        public BindingState<Buffer.Type, Buffer> state() {
            return state;
        }

    }

    enum DataUsage {
        STREAM_DRAW("STREAM_DRAW"),
        STREAM_READ("STREAM_READ"),
        STREAM_COPY("STREAM_COPY"),
        STATIC_DRAW("STATIC_DRAW"),
        STATIC_READ("STATIC_READ"),
        STATIC_COPY("STATIC_COPY"),
        DYNAMIC_DRAW("DYNAMIC_DRAW"),
        DYNAMIC_READ("DYNAMIC_READ"),
        DYNAMIC_COPY("DYNAMIC_COPY");

        final int value;

        private DataUsage(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    enum StorageFlags {
        MapRead("MAP_READ_BIT"),
        MapWrite("MAP_WRITE_BIT");
        private final int value;

        StorageFlags(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }

        public static int value(StorageFlags... flags) {
            if (null == flags || flags.length == 0) {
                return 0;
            }
            int ret = 0;
            for (var flag : flags) {
                ret |= flag.value;
            }
            return ret;
        }
    }

    DataType dataType();

    default int count() {
        return bytes() / dataType().bytes();
    }

    Buffer realloc(DataUsage usage, DoubleBuffer data);

    Buffer realloc(DataUsage usage, FloatBuffer data);

    Buffer realloc(DataUsage usage, IntBuffer data);

    Buffer realloc(DataUsage usage, ShortBuffer data);

    Buffer realloc(DataUsage usage, ByteBuffer data);

    Buffer realloc(DataUsage usage, double[] data);

    Buffer realloc(DataUsage usage, float[] data);

    Buffer realloc(DataUsage usage, int[] data);

    Buffer realloc(DataUsage usage, short[] data);

    Buffer storage(DoubleBuffer data, StorageFlags... flags);

    Buffer storage(FloatBuffer data, StorageFlags... flags);

    Buffer storage(IntBuffer data, StorageFlags... flags);

    Buffer storage(ShortBuffer data, StorageFlags... flags);

    Buffer storage(ByteBuffer data, StorageFlags... flags);

    Buffer storage(double[] data, StorageFlags... flags);

    Buffer storage(float[] data, StorageFlags... flags);

    Buffer storage(int[] data, StorageFlags... flags);

    Buffer storage(short[] data, StorageFlags... flags);

    Buffer set(int offset, DoubleBuffer data);

    Buffer set(int offset, FloatBuffer data);

    Buffer set(int offset, IntBuffer data);

    Buffer set(int offset, ShortBuffer data);

    Buffer set(int offset, ByteBuffer data);

    Buffer set(int offset, double[] data);

    Buffer set(int offset, float[] data);

    Buffer set(int offset, int[] data);

    Buffer set(int offset, short[] data);

    default Buffer accept(Consumer<Buffer> consumer) {
        consumer.accept(this);
        return this;
    }

    int bytes();

    static Buffer of(Type type, DataType dataType) {
        return Meta.create(type, dataType);
    }

    static CloseableArray<Buffer> of(int n, Type type, DataType dataType) {
        return Meta.createArray(n, type, dataType);
    }

    static Buffer ofArray(DataType dataType) {
        return of(Type.Array, dataType);
    }

    static CloseableArray<Buffer> ofArray(int n, DataType dataType) {
        return of(n, Type.Array, dataType);
    }

    static Buffer ofElementArray(DataType dataType) {
        return of(Type.ElementArray, dataType);
    }

    static CloseableArray<Buffer> ofElementArray(int n, DataType dataType) {
        return of(n, Type.ElementArray, dataType);
    }
}
