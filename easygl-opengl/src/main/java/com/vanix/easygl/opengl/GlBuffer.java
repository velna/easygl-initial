package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractMultiTargetBindable;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DataType;

import java.nio.*;
import java.util.function.IntConsumer;

public class GlBuffer extends AbstractMultiTargetBindable<Buffer.Type, Buffer> implements Buffer {
    private final DataType dataType;
    private int bytes;

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
        this.bytes = dataBytes;
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

    private <T> Buffer set(SubDataFunction<T> subDataFunction, int offset, T data, int dataBytes) {
        assertBinding();
        subDataFunction.accept(target().value(), offset, data);
        GLX.checkError();
        this.bytes = offset + dataBytes;
        return this;
    }

    @Override
    public Buffer set(int offset, DoubleBuffer data) {
        return set(GLX::glBufferSubData, DataType.Double.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, FloatBuffer data) {
        return set(GLX::glBufferSubData, DataType.Float.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, IntBuffer data) {
        return set(GLX::glBufferSubData, DataType.Int.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, ShortBuffer data) {
        return set(GLX::glBufferSubData, DataType.Short.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, ByteBuffer data) {
        return set(GLX::glBufferSubData, DataType.Byte.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, double[] data) {
        return set(GLX::glBufferSubData, DataType.Double.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, float[] data) {
        return set(GLX::glBufferSubData, DataType.Float.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, int[] data) {
        return set(GLX::glBufferSubData, DataType.Int.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, short[] data) {
        return set(GLX::glBufferSubData, DataType.Short.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    private interface StorageFunction<T> {
        void accept(int target, T data, int flags);
    }

    private <T> Buffer storage(StorageFunction<T> storageFn, T data, int dataBytes, StorageFlags... flags) {
        assertBinding();
        storageFn.accept(target.value(), data, StorageFlags.value(flags));
        GLX.checkError();
        this.bytes = dataBytes;
        return this;
    }

    @Override
    public Buffer storage(DoubleBuffer data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(FloatBuffer data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(IntBuffer data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(ShortBuffer data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(ByteBuffer data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(double[] data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(float[] data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(int[] data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(short[] data, StorageFlags... flags) {
        return storage(GLX::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public int bytes() {
        return bytes;
    }

    @Override
    public Buffer bindRange(int index, long offset, long size) {
        assertBinding();
        GLX.glBindBufferRange(target.value(), index, intHandle(), offset, size);
        GLX.checkError();
        return this;
    }
}
