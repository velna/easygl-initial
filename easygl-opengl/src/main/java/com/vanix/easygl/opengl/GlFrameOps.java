package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.graphics.FrameBuffers;
import com.vanix.easygl.core.graphics.FrameColorBuffer;
import com.vanix.easygl.core.graphics.FrameOps;
import com.vanix.easygl.core.graphics.Texture;

public interface GlFrameOps<T extends FrameOps<T>> extends FrameOps<T> {
    @SuppressWarnings("unchecked")
    default T self() {
        return (T) this;
    }

    @Override
    default T clear(FrameBuffers attr) {
        GLX.glClear(attr.value());
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
    default T selectDrawBuffer(FrameColorBuffer frameColorBuffer) {
        GLX.glDrawBuffer(frameColorBuffer.value());
        return self();
    }

    @Override
    default T selectDrawBuffers(FrameColorBuffer.MultiSelectable... drawBuffers) {
        int[] values = new int[drawBuffers.length];
        for (int i = 0; i < drawBuffers.length; i++) {
            values[i] = drawBuffers[i].value();
        }
        GLX.glDrawBuffers(values);
        return self();
    }

    @Override
    default T blit(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, FrameBuffers buffers, Texture.MagFilter filter) {
        GLX.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, buffers.value(), filter.value());
        return self();
    }
}
