package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.AbstractBindableHandle;
import com.vanix.easygl.core.graphics.Buffer;
import com.vanix.easygl.core.graphics.DataType;

import java.nio.*;

public class GlBuffer extends AbstractBindableHandle<Buffer> implements Buffer {

    private final Type type;

    private final DataType dataType;
    private int bytes;

    public GlBuffer(Type type, DataType dataType) {
        super(GLC.glGenBuffers(), type.state());
        this.type = type;
        this.dataType = dataType;
    }

    @Override
    protected void bind(int handle) {
        GLC.glBindBuffer(type.value(), handle);
    }

    @Override
    protected void close(int handle) {
        GLC.glDeleteBuffers(handle);
    }

    @Override
    public Type type() {
        return type;
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
        reallocFn.accept(type().value(), data, usage.value());
        GLC.checkError();
        this.bytes = dataBytes;
        return this;
    }

    @Override
    public Buffer realloc(DataUsage usage, DoubleBuffer data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, FloatBuffer data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, IntBuffer data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, ShortBuffer data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, ByteBuffer data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, double[] data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, float[] data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, int[] data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    @Override
    public Buffer realloc(DataUsage usage, short[] data) {
        return realloc(GLC::glBufferData, usage, data, DataType.bytesOf(data));
    }

    private interface SubDataFunction<T> {
        void accept(int target, long offset, T data);
    }

    private <T> Buffer set(SubDataFunction<T> subDataFunction, int offset, T data, int dataBytes) {
        assertBinding();
        subDataFunction.accept(type().value(), offset, data);
        GLC.checkError();
        this.bytes = offset + dataBytes;
        return this;
    }

    @Override
    public Buffer set(int offset, DoubleBuffer data) {
        return set(GLC::glBufferSubData, DataType.Double.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, FloatBuffer data) {
        return set(GLC::glBufferSubData, DataType.Float.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, IntBuffer data) {
        return set(GLC::glBufferSubData, DataType.Int.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, ShortBuffer data) {
        return set(GLC::glBufferSubData, DataType.Short.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, ByteBuffer data) {
        return set(GLC::glBufferSubData, DataType.Byte.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, double[] data) {
        return set(GLC::glBufferSubData, DataType.Double.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, float[] data) {
        return set(GLC::glBufferSubData, DataType.Float.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, int[] data) {
        return set(GLC::glBufferSubData, DataType.Int.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    @Override
    public Buffer set(int offset, short[] data) {
        return set(GLC::glBufferSubData, DataType.Short.bytesOfCount(offset), data, DataType.bytesOf(data));
    }

    private interface StorageFunction<T> {
        void accept(int target, T data, int flags);
    }

    private <T> Buffer storage(StorageFunction<T> storageFn, T data, int dataBytes, StorageFlags... flags) {
        assertBinding();
        storageFn.accept(type.value(), data, StorageFlags.value(flags));
        GLC.checkError();
        this.bytes = dataBytes;
        return this;
    }

    @Override
    public Buffer storage(DoubleBuffer data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(FloatBuffer data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(IntBuffer data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(ShortBuffer data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(ByteBuffer data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(double[] data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(float[] data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(int[] data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public Buffer storage(short[] data, StorageFlags... flags) {
        return storage(GLC::glBufferStorage, data, DataType.bytesOf(data), flags);
    }

    @Override
    public int bytes() {
        return bytes;
    }
}
