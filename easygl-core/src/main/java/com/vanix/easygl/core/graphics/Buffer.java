package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.graphics.gl.GLC;
import com.vanix.easygl.core.graphics.gl.GlBuffer;

import java.nio.*;
import java.util.function.Consumer;

public interface Buffer extends Handle, Bindable<Buffer.Type, Buffer> {

    enum Type implements BindTarget<Buffer.Type, Buffer> {
        Array(GLC.GL_ARRAY_BUFFER),
        CopyRead(GLC.GL_COPY_READ_BUFFER),
        CopyWrite(GLC.GL_COPY_WRITE_BUFFER),
        ElementArray(GLC.GL_ELEMENT_ARRAY_BUFFER),
        PixelPack(GLC.GL_PIXEL_PACK_BUFFER),
        PixelUnpack(GLC.GL_PIXEL_UNPACK_BUFFER),
        Texture(GLC.GL_TEXTURE_BUFFER),
        TransformFeedback(GLC.GL_TRANSFORM_FEEDBACK_BUFFER),
        Uniform(GLC.GL_UNIFORM_BUFFER);

        final int value;

        private final BindingState<Buffer.Type, Buffer> state;

        Type(int value) {
            this.value = value;
            state = BindingState.ofInt(name(), h -> GLC.glBindBuffer(value, h));
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
        STREAM_DRAW(GLC.GL_STREAM_DRAW),
        STREAM_READ(GLC.GL_STREAM_READ),
        STREAM_COPY(GLC.GL_STREAM_COPY),
        STATIC_DRAW(GLC.GL_STATIC_DRAW),
        STATIC_READ(GLC.GL_STATIC_READ),
        STATIC_COPY(GLC.GL_STATIC_COPY),
        DYNAMIC_DRAW(GLC.GL_DYNAMIC_DRAW),
        DYNAMIC_READ(GLC.GL_DYNAMIC_READ),
        DYNAMIC_COPY(GLC.GL_DYNAMIC_COPY);

        final int value;

        private DataUsage(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    enum StorageFlags {
        MapRead(GLC.GL_MAP_READ_BIT),
        MapWrite(GLC.GL_MAP_WRITE_BIT);
        private final int value;

        StorageFlags(int value) {
            this.value = value;
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
        return new GlBuffer(type, dataType);
    }

    static Buffer ofArray(VertexArray vao, DataType dataType) {
        vao.bind();
        return of(Type.Array, dataType);
    }

    static Buffer ofElementArray(VertexArray vao, DataType dataType) {
        vao.bind();
        return of(Type.ElementArray, dataType);
    }
}
