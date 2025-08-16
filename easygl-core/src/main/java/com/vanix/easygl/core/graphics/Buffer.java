package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.BindingState;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.MultiTargetBindable;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.*;

public interface Buffer extends Handle, MultiTargetBindable<Buffer.Type, Buffer> {

    enum Type implements BindTarget<Type, Buffer>, IntEnum {
        Array("ARRAY_BUFFER"),
        AtomicCounter("ATOMIC_COUNTER_BUFFER"),
        CopyRead("COPY_READ_BUFFER"),
        CopyWrite("COPY_WRITE_BUFFER"),
        DispatchIndirect("DISPATCH_INDIRECT_BUFFER"),
        DrawIndirect("DRAW_INDIRECT_BUFFER"),
        ElementArray("ELEMENT_ARRAY_BUFFER"),
        PixelPack("PIXEL_PACK_BUFFER"),
        PixelUnpack("PIXEL_UNPACK_BUFFER"),
        Query("QUERY_BUFFER"),
        ShaderStorage("SHADER_STORAGE_BUFFER"),
        Texture("TEXTURE_BUFFER"),
        TransformFeedback("TRANSFORM_FEEDBACK_BUFFER"),
        Uniform("UNIFORM_BUFFER");

        final int value;

        private final BindingState<Type, Buffer> state;

        Type(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
            state = MetaHolder.Buffer.newBindingState(name());
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

    enum DataUsage implements IntEnum {
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

        DataUsage(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum StorageFlags {
        MapRead("MAP_READ_BIT"),
        MapWrite("MAP_WRITE_BIT"),
        MapReadWrite(MapRead, MapWrite);
        private final int value;

        StorageFlags(StorageFlags f1, StorageFlags f2) {
            this.value = f1.value | f2.value;
        }

        StorageFlags(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    DataType dataType();

    default long count() {
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

    Buffer storage(DoubleBuffer data, StorageFlags flags);

    Buffer storage(FloatBuffer data, StorageFlags flags);

    Buffer storage(IntBuffer data, StorageFlags flags);

    Buffer storage(ShortBuffer data, StorageFlags flags);

    Buffer storage(ByteBuffer data, StorageFlags flags);

    Buffer storage(double[] data, StorageFlags flags);

    Buffer storage(float[] data, StorageFlags flags);

    Buffer storage(int[] data, StorageFlags flags);

    Buffer storage(short[] data, StorageFlags flags);

    Buffer set(int offset, DoubleBuffer data);

    Buffer set(int offset, FloatBuffer data);

    Buffer set(int offset, IntBuffer data);

    Buffer set(int offset, ShortBuffer data);

    Buffer set(int offset, ByteBuffer data);

    Buffer set(int offset, double[] data);

    Buffer set(int offset, float[] data);

    Buffer set(int offset, int[] data);

    Buffer set(int offset, short[] data);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ByteBuffer data);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, short[] data);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ShortBuffer data);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, int[] data);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, IntBuffer data);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, float[] data);

    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, FloatBuffer data);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ByteBuffer data);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, short[] data);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ShortBuffer data);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, int[] data);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, IntBuffer data);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, float[] data);

    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, FloatBuffer data);

    ByteBuffer mapRange(long offset, long size, MapAccess access);

    ByteBuffer mapRange(long offset, long size, BitSet<MapAccess> access);

    ByteBuffer map(MapAccess access);

    ByteBuffer map(BitSet<MapAccess> access);

    Buffer flushMappedRange(long offset, long size);

    boolean unmap();

    Buffer invalidateSubData(long offset, long size);

    Buffer invalidateData();

    Buffer getSubData(long offset, short[] data);

    Buffer getSubData(long offset, ShortBuffer data);

    Buffer getSubData(long offset, int[] data);

    Buffer getSubData(long offset, IntBuffer data);

    Buffer getSubData(long offset, float[] data);

    Buffer getSubData(long offset, FloatBuffer data);

    Buffer getSubData(long offset, long[] data);

    Buffer getSubData(long offset, LongBuffer data);

    Buffer getSubData(long offset, double[] data);

    Buffer getSubData(long offset, DoubleBuffer data);

    Buffer getSubData(long offset, ByteBuffer data);

    Buffer copySubData(long readOffset, Buffer dstBuffer, long writeOffset, long size);

    long bytes();

    Buffer bindRange(int index, long offset, long size);

    Buffer bindBase(int index);

    static Buffer of(DataType dataType) {
        return MetaHolder.Buffer.create(dataType);
    }

    static BufferArray of(int n, DataType dataType) {
        return (BufferArray) MetaHolder.Buffer.createArray(n, dataType);
    }

}
