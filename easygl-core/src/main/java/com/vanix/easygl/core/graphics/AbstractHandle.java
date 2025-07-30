package com.vanix.easygl.core.graphics;

public abstract class AbstractHandle implements Handle {
    private final boolean isLong;
    protected final int intHandle;
    protected final long longHandle;
    private boolean closed;

    protected AbstractHandle(int handle) {
        this.intHandle = handle;
        this.longHandle = -1;
        isLong = false;
    }

    protected AbstractHandle(long handle) {
        this.longHandle = handle;
        this.intHandle = -1;
        isLong = true;
    }

    protected abstract void close(int handle);

    protected void close(long handle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        if (!closed) {
            if (isLong) {
                close(longHandle);
            } else {
                close(intHandle);
            }
            closed = true;
        }
    }

    @Override
    public int handle() {
        if (isLong) {
            throw new UnsupportedOperationException();
        }
        return intHandle;
    }

    @Override
    public long longHandle() {
        if (!isLong) {
            throw new UnsupportedOperationException();
        }
        return longHandle;
    }
}
