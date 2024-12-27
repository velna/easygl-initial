package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;
import org.joml.Vector4i;

public class GlFrameBuffer extends AbstractBindableHandle<FrameBuffer> implements FrameBuffer {
    public static final FrameBuffer DefaultFrameBuffer = new GlFrameBuffer(0);
    private int target = GLC.GL_FRAMEBUFFER;

    public GlFrameBuffer(int handle) {
        super(handle, State);
    }

    public GlFrameBuffer() {
        super(GLC.glGenFramebuffers(), State);
    }

    @Override
    protected void bind(int handle) {
        GLC.glBindFramebuffer(target, handle);
    }

    @Override
    protected void close(int handle) {
        GLC.glDeleteFramebuffers(handle);
    }

    @Override
    public FrameBuffer readOnly() {
        target = GLC.GL_READ_FRAMEBUFFER;
        return this;
    }

    @Override
    public FrameBuffer writeOnly() {
        target = GLC.GL_DRAW_FRAMEBUFFER;
        return this;
    }

    @Override
    public FrameBuffer readWrite() {
        target = GLC.GL_FRAMEBUFFER;
        return this;
    }

    @Override
    public FrameBuffer attach(Attachment attachment, Texture2D texture, int level) {
        assertBinding();
        GLC.glFramebufferTexture2D(target, attachment.value(), texture.type().value(), texture.handle(), level);
        GLC.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(Attachment attachment, RenderBuffer renderBuffer) {
        assertBinding();
        GLC.glFramebufferRenderbuffer(target, attachment.value(), GLC.GL_RENDERBUFFER, renderBuffer.handle());
        GLC.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearColor(DrawBufferIndex index, Vector4f color) {
        assertBinding();
        GLC.glClearBufferfv(GLC.GL_COLOR, index.ordinal(), new float[]{color.x, color.y, color.z, color.w});
        GLC.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearColor(DrawBufferIndex index, Vector4i color) {
        assertBinding();
        GLC.glClearBufferiv(GLC.GL_COLOR, index.ordinal(), new int[]{color.x, color.y, color.z, color.w});
        GLC.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearColorUnsigned(DrawBufferIndex index, Vector4i color) {
        assertBinding();
        GLC.glClearBufferuiv(GLC.GL_COLOR, index.ordinal(), new int[]{color.x, color.y, color.z, color.w});
        GLC.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearDepth(float value) {
        assertBinding();
        GLC.glClearBufferfv(GLC.GL_DEPTH, 0, new float[]{value});
        GLC.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearStencil(int value) {
        assertBinding();
        GLC.glClearBufferiv(GLC.GL_STENCIL, 0, new int[]{value});
        GLC.checkError();
        return this;
    }
}
