package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.graphics.DrawBuffer;
import com.vanix.easygl.core.graphics.FrameBufferOps;

public interface GlFrameBufferOps<T extends FrameBufferOps<T>> extends FrameBufferOps<T> {
    @SuppressWarnings("unchecked")
    default T self() {
        return (T) this;
    }

    @Override
    default T clear(FrameBufferOps.BufferMask attr) {
        GLX.glClear(attr.value());
        return self();
    }

    @Override
    default T clear(FrameBufferOps.BufferMask attr1, FrameBufferOps.BufferMask attr2) {
        GLX.glClear(attr1.value() | attr2.value());
        return self();
    }

    @Override
    default T clear(FrameBufferOps.BufferMask attr1, FrameBufferOps.BufferMask attr2, FrameBufferOps.BufferMask attr3) {
        GLX.glClear(attr1.value() | attr2.value() | attr3.value());
        return self();
    }

    @Override
    default T setClearColor(float red, float blue, float green, float alpha) {
        GLX.glClearColor(red, blue, green, alpha);
        return self();
    }

    @Override
    default Color getClearColor() {
        float[] data = new float[4];
        GLX.glGetFloatv(GLX.GL_COLOR_CLEAR_VALUE, data);
        return new Color(data[0], data[1], data[2], data[3]);
    }

    @Override
    default T setClearDepth(float depth) {
        GLX.glClearDepth(depth);
        return self();
    }

    @Override
    default float getClearDepth() {
        return GLX.glGetFloat(GLX.GL_DEPTH_CLEAR_VALUE);
    }

    @Override
    default T setClearStencil(int s) {
        GLX.glClearStencil(s);
        return self();
    }

    @Override
    default int getClearStencil() {
        return GLX.glGetInteger(GLX.GL_STENCIL_CLEAR_VALUE);
    }

    @Override
    default T selectDrawBuffer(DrawBuffer drawBuffer) {
        GLX.glDrawBuffer(drawBuffer.value());
        return self();
    }

    @Override
    default T selectDrawBuffers(DrawBuffer.MultiSelectable... drawBuffers) {
        int[] values = new int[drawBuffers.length];
        for (int i = 0; i < drawBuffers.length; i++) {
            values[i] = drawBuffers[i].value();
        }
        GLX.glDrawBuffers(values);
        return self();
    }
}
