package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;
import org.joml.Vector4i;

import java.util.function.IntConsumer;

public class GlFrame extends AbstractHandle implements Frame {
    public static final Frame DEFAULT_FRAME = new GlFrame(0);

    protected GlFrame() {
        this(GLX.glGenFramebuffers());
    }

    protected GlFrame(int handle) {
        super(handle, (IntConsumer) GLX::glDeleteFramebuffers);
    }

    @Override
    public Frame attach(Target target, FrameAttachment attachment, Texture2D texture, int level) {
        GLX.glFramebufferTexture2D(target.value(), attachment.value(), texture.target().value(), texture.intHandle(), level);
        GLX.checkError();
        return this;
    }

    @Override
    public Frame attach(Target target, FrameAttachment.Renderable attachment, RenderBuffer renderBuffer) {
        GLX.glFramebufferRenderbuffer(target.value(), attachment.value(), GLX.GL_RENDERBUFFER, renderBuffer.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public Frame detachRenderBuffer(Target target, FrameAttachment.Renderable attachment) {
        GLX.glFramebufferRenderbuffer(target.value(), attachment.value(), GLX.GL_RENDERBUFFER, 0);
        return this;
    }

    @Override
    public Frame clear(FrameBuffers attr) {
        GLX.glClear(attr.value());
        return this;
    }

    @Override
    public Frame setClearColor(float red, float blue, float green, float alpha) {
        GLX.glClearColor(red, blue, green, alpha);
        return this;
    }

    @Override
    public Color getClearColor() {
        float[] data = new float[4];
        GLX.glGetFloatv(GLX.GL_COLOR_CLEAR_VALUE, data);
        return new Color(data[0], data[1], data[2], data[3]);
    }

    @Override
    public Frame setClearDepth(float depth) {
        GLX.glClearDepth(depth);
        return this;
    }

    @Override
    public float getClearDepth() {
        return GLX.glGetFloat(GLX.GL_DEPTH_CLEAR_VALUE);
    }

    @Override
    public Frame setClearStencil(int s) {
        GLX.glClearStencil(s);
        return this;
    }

    @Override
    public int getClearStencil() {
        return GLX.glGetInteger(GLX.GL_STENCIL_CLEAR_VALUE);
    }

    @Override
    public Frame selectDrawBuffer(FrameColorBuffer frameColorBuffer) {
        GLX.glDrawBuffer(frameColorBuffer.value());
        return this;
    }

    @Override
    public Frame selectDrawBuffers(FrameColorBuffer.MultiSelectable... drawBuffers) {
        int[] values = new int[drawBuffers.length];
        for (int i = 0; i < drawBuffers.length; i++) {
            values[i] = drawBuffers[i].value();
        }
        GLX.glDrawBuffers(values);
        return this;
    }

    @Override
    public Frame blit(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, FrameBuffers buffers, Texture.MagFilter filter) {
        GLX.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, buffers.value(), filter.value());
        return this;
    }

    @Override
    public Frame clearColor(DrawBufferIndex index, Vector4f color) {
        GLX.glClearBufferfv(GLX.GL_COLOR, index.ordinal(), new float[]{color.x, color.y, color.z, color.w});
        GLX.checkError();
        return this;
    }

    @Override
    public Frame clearColor(DrawBufferIndex index, Vector4i color) {
        GLX.glClearBufferiv(GLX.GL_COLOR, index.ordinal(), new int[]{color.x, color.y, color.z, color.w});
        GLX.checkError();
        return this;
    }

    @Override
    public Frame clearColorUnsigned(DrawBufferIndex index, Vector4i color) {
        GLX.glClearBufferuiv(GLX.GL_COLOR, index.ordinal(), new int[]{color.x, color.y, color.z, color.w});
        GLX.checkError();
        return this;
    }
}
