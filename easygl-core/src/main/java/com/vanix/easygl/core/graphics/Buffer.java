package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.*;
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

    enum StorageBits implements IntEnum {
        Dynamic("DYNAMIC_STORAGE_BIT"),
        Client("CLIENT_STORAGE_BIT"),
        Read("MAP_READ_BIT"),
        Write("MAP_WRITE_BIT"),
        @Support(since = Version.GL44)
        PersistentRead("MAP_PERSISTENT_BIT", Read),
        @Support(since = Version.GL44)
        PersistentWrite("MAP_PERSISTENT_BIT", Write),
        @Support(since = Version.GL44)
        Coherent("MAP_COHERENT_BIT", "MAP_PERSISTENT_BIT");
        private final int value;

        StorageBits(String id, StorageBits ma2) {
            this.value = MetaSystem.Graphics.queryInt(id) | ma2.value;
        }

        StorageBits(String id1, String id2) {
            this.value = MetaSystem.Graphics.queryInt(id1) | MetaSystem.Graphics.queryInt(id2);
        }

        StorageBits(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    DataType dataType();

    default long count() {
        return bytes() / dataType().bytes();
    }

    //region Set buffer data
    Buffer realloc(DataUsage usage, DoubleBuffer data);

    Buffer realloc(DataUsage usage, FloatBuffer data);

    Buffer realloc(DataUsage usage, IntBuffer data);

    Buffer realloc(DataUsage usage, ShortBuffer data);

    Buffer realloc(DataUsage usage, ByteBuffer data);

    Buffer realloc(DataUsage usage, double[] data);

    Buffer realloc(DataUsage usage, float[] data);

    Buffer realloc(DataUsage usage, int[] data);

    Buffer realloc(DataUsage usage, short[] data);
    //endregion

    //region Data storage
    @Support(since = Version.GL44)
    Buffer storage(DoubleBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(FloatBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(IntBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(ShortBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(ByteBuffer data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(double[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(float[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(int[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(short[] data, StorageBits flags);

    @Support(since = Version.GL44)
    Buffer storage(DoubleBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(FloatBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(IntBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(ShortBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(ByteBuffer data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(double[] data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(float[] data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(int[] data, BitSet<StorageBits> flags);

    @Support(since = Version.GL44)
    Buffer storage(short[] data, BitSet<StorageBits> flags);
    //endregion

    //region Set sub data
    Buffer set(int offset, DoubleBuffer data);

    Buffer set(int offset, FloatBuffer data);

    Buffer set(int offset, IntBuffer data);

    Buffer set(int offset, ShortBuffer data);

    Buffer set(int offset, ByteBuffer data);

    Buffer set(int offset, double[] data);

    Buffer set(int offset, float[] data);

    Buffer set(int offset, int[] data);

    Buffer set(int offset, short[] data);
    //endregion

    //region Clear data
    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ByteBuffer data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, short[] data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ShortBuffer data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, int[] data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, IntBuffer data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, float[] data);

    @Support(since = Version.GL40)
    Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, FloatBuffer data);
    //endregion

    //region Clear sub data
    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ByteBuffer data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, short[] data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ShortBuffer data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, int[] data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, IntBuffer data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, float[] data);

    @Support(since = Version.GL40)
    Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, FloatBuffer data);
    //endregion

    //region Data mapping
    ByteBuffer mapRange(long offset, long size, MapAccessBits access);

    ByteBuffer mapRange(long offset, long size, BitSet<MapAccessBits> access);

    ByteBuffer map(MapAccessBits access);

    ByteBuffer map(BitSet<MapAccessBits> access);

    Buffer flushMappedRange(long offset, long size);

    boolean unmap();
    //endregion

    @Support(since = Version.GL40)
    Buffer invalidateSubData(long offset, long size);

    @Support(since = Version.GL40)
    Buffer invalidateData();

    //region Get buffer data
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
    //endregion

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
