package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;
import org.joml.Vector4i;

public class GlFrameBuffer extends AbstractBindable<FrameBuffer.Type, FrameBuffer> implements FrameBuffer {
    public static final FrameBuffer DefaultFrameBuffer = new GlFrameBuffer(Type.FrameBuffer, 0);

    public GlFrameBuffer(Type type, int handle) {
        super(handle, type);
    }

    public GlFrameBuffer() {
        super(GLC.glGenFramebuffers(), Type.FrameBuffer);
    }

    @Override
    protected void close(int handle) {
        GLC.glDeleteFramebuffers(handle);
    }

    @Override
    public FrameBuffer attach(Attachment attachment, Texture2D texture, int level) {
        assertBinding();
        GLC.glFramebufferTexture2D(targetValue(), attachment.value(), texture.target().value(), texture.handle(), level);
        GLC.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(Attachment attachment, RenderBuffer renderBuffer) {
        assertBinding();
        GLC.glFramebufferRenderbuffer(targetValue(), attachment.value(), GLC.GL_RENDERBUFFER, renderBuffer.handle());
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
