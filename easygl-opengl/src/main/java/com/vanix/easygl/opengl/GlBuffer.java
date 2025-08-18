package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.BitSet;
import com.vanix.easygl.core.AbstractMultiTargetBindable;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.*;

import java.nio.*;
import java.util.function.IntConsumer;

public class GlBuffer extends AbstractMultiTargetBindable<Buffer.Type, Buffer> implements Buffer {
    private final DataType dataType;
    private long sizeInBytes;

    protected GlBuffer(int handle, DataType dataType) {
        super(handle, (IntConsumer) GLX::glDeleteBuffers);
        this.dataType = dataType;
    }

    protected GlBuffer(DataType dataType) {
        this(GLX.glGenBuffers(), dataType);
    }

    @Override
    public DataType dataType() {
        return dataType;
    }

    private interface ReallocFunction<T> {
        void accept(int target, T data, int usage);
    }

    private <T> Buffer realloc(ReallocFunction<T> reallocFn, DataUsage usage, T data, int dataBytes) {
        assertBinding();
        reallocFn.accept(target().value(), data, usage.value());
        GLX.checkError();
        sizeInBytes = dataBytes;
        return this;
    }

    @Override
    public Buffer realloc(DataUsage usage, DoubleBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, FloatBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, IntBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, ShortBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, ByteBuffer data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, double[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, float[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, int[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, short[] data) {
        return realloc(GLX::glBufferData, usage, data, DataType.bytesOf(data));
    }

    private interface SubDataFunction<T> {
        void accept(int target, long offset, T data);
    }

    private <T> Buffer setSubData(SubDataFunction<T> subDataFunction, int offset, T data, int dataBytes) {
        assertBinding();
        subDataFunction.accept(target().value(), offset, data);
        GLX.checkError();
        return this;
    }

    @Override
    public Buffer setSubData(int offset, DoubleBuffer data) {
        return setSubData(GLX::glBufferSubData, DataType.Double.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, FloatBuffer data) {
        return setSubData(GLX::glBufferSubData, DataType.Float.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, IntBuffer data) {
        return setSubData(GLX::glBufferSubData, DataType.Int.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, ShortBuffer data) {
        return setSubData(GLX::glBufferSubData, DataType.Short.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, ByteBuffer data) {
        return setSubData(GLX::glBufferSubData, DataType.Byte.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, double[] data) {
        return setSubData(GLX::glBufferSubData, DataType.Double.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, float[] data) {
        return setSubData(GLX::glBufferSubData, DataType.Float.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, int[] data) {
        return setSubData(GLX::glBufferSubData, DataType.Int.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer setSubData(int offset, short[] data) {
        return setSubData(GLX::glBufferSubData, DataType.Short.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    private interface StorageFunction<T> {
        void accept(int target, T data, int flags);
    }

    private <T> Buffer storage(StorageFunction<T> storageFn, T data, int dataBytes, int flags) {
        assertBinding();
        storageFn.accept(target.value(), data, flags);
        GLX.checkError();
        sizeInBytes = dataBytes;
        return this;
    }

    @Override
    public Buffer storage(DoubleBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(FloatBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(IntBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(ShortBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(ByteBuffer data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(double[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(float[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(int[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(short[] data, StorageBits flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(DoubleBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(FloatBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(IntBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(ShortBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(ByteBuffer data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(double[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(float[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(int[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    @Override
    public Buffer storage(short[] data, BitSet<StorageBits> flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags.value());
    }

    private interface ClearDataFunction<T> {
        void accept(int target, int internalFormat, int format, int type, T data);
    }

    private <T> Buffer clearData(ClearDataFunction<T> clearFunction, InternalPixelFormat internalFormat, PixelFormat format, DataType type, T data) {
        assertBinding();
        clearFunction.accept(target.value(), internalFormat.value(), format.value(), type.value(), data);
        GLX.checkError();
        return this;
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, (ByteBuffer) null);
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ByteBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, short[] data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, ShortBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, int[] data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, IntBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, float[] data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    @Override
    public Buffer clearData(InternalPixelFormat internalFormat, PixelFormat format, DataType type, FloatBuffer data) {
        return clearData(GLX::glClearBufferData, internalFormat, format, type, data);
    }

    private interface ClearSubDataFunction<T> {
        void accept(int target, int internalFormat, long offset, long size, int format, int type, T data);
    }

    private <T> Buffer clearSubData(ClearSubDataFunction<T> clearFunction, InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, T data) {
        assertBinding();
        clearFunction.accept(target.value(), internalFormat.value(), offset, size, format.value(), type.value(), data);
        GLX.checkError();
        return this;
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, (ByteBuffer) null);
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ByteBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, short[] data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, ShortBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, int[] data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, IntBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, float[] data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public Buffer clearSubData(InternalPixelFormat internalFormat, long offset, long size, PixelFormat format, DataType type, FloatBuffer data) {
        return clearSubData(GLX::glClearBufferSubData, internalFormat, offset, size, format, type, data);
    }

    @Override
    public ByteBuffer mapRange(long offset, long size, MapAccessBits access) {
        assertBinding();
        var ret = GLX.glMapBufferRange(target.value(), offset, size, access.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public ByteBuffer mapRange(long offset, long size, BitSet<MapAccessBits> access) {
        assertBinding();
        var ret = GLX.glMapBufferRange(target.value(), offset, size, access.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public ByteBuffer map(MapAccessBits access) {
        assertBinding();
        var ret = GLX.glMapBuffer(target.value(), access.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public ByteBuffer map(BitSet<MapAccessBits> access) {
        assertBinding();
        var ret = GLX.glMapBuffer(target.value(), access.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public Buffer flushMappedRange(long offset, long size) {
        assertBinding();
        GLX.glFlushMappedBufferRange(target.value(), offset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public boolean unmap() {
        assertBinding();
        boolean ret = GLX.glUnmapBuffer(target.value());
        GLX.checkError();
        return ret;
    }

    @Override
    public Buffer invalidateSubData(long offset, long size) {
        GLX.glInvalidateBufferSubData(intHandle(), offset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public Buffer invalidateData() {
        GLX.glInvalidateBufferData(intHandle());
        GLX.checkError();
        return this;
    }

    private interface GetSubDataFunction<T> {
        void accept(int target, long offset, T data);
    }

    private <T> Buffer getSubData(GetSubDataFunction<T> getSubDataFunction, long offset, T data) {
        assertBinding();
        getSubDataFunction.accept(target.value(), offset, data);
        GLX.checkError();
        return this;
    }

    @Override
    public Buffer getSubData(long offset, short[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, ShortBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, int[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, IntBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, float[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, FloatBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, long[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, LongBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, double[] data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, DoubleBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer getSubData(long offset, ByteBuffer data) {
        return getSubData(GLX::glGetBufferSubData, offset, data);
    }

    @Override
    public Buffer copySubData(long readOffset, Buffer dstBuffer, long writeOffset, long size) {
        assertBinding();
        GLX.glCopyBufferSubData(target.value(), dstBuffer.target().value(), readOffset, writeOffset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public long bytes() {
        return sizeInBytes;
    }

    @Override
    public Buffer bindRange(int index, long offset, long size) {
        assertBinding();
        GLX.glBindBufferRange(target.value(), index, intHandle(), offset, size);
        GLX.checkError();
        return this;
    }

    @Override
    public Buffer bindBase(int index) {
        assertBinding();
        GLX.glBindBufferBase(target.value(), index, intHandle());
        GLX.checkError();
        return this;
    }
}
