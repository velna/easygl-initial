package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;
import org.joml.Vector4i;

import java.util.function.IntConsumer;

public class GlFrameBuffer extends AbstractBindable<FrameBuffer.Type, FrameBuffer> implements FrameBuffer {
    public static final FrameBuffer DefaultFrameBuffer = new GlFrameBuffer(0, new Object[]{Type.FrameBuffer});

    protected GlFrameBuffer(Object... args) {
        this(GLX.glGenFramebuffers(), args);
    }

    protected GlFrameBuffer(int handle, Object... args) {
        super(handle, (Type) args[0], (IntConsumer) GLX::glDeleteFramebuffers);
    }

    @Override
    public FrameBuffer attach(Attachment attachment, Texture2D texture, int level) {
        assertBinding();
        GLX.glFramebufferTexture2D(targetValue(), attachment.value(), texture.target().value(), texture.intHandle(), level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(Attachment attachment, RenderBuffer renderBuffer) {
        assertBinding();
        GLX.glFramebufferRenderbuffer(targetValue(), attachment.value(), GLX.GL_RENDERBUFFER, renderBuffer.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearColor(DrawBufferIndex index, Vector4f color) {
        assertBinding();
        GLX.glClearBufferfv(GLX.GL_COLOR, index.ordinal(), new float[]{color.x, color.y, color.z, color.w});
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearColor(DrawBufferIndex index, Vector4i color) {
        assertBinding();
        GLX.glClearBufferiv(GLX.GL_COLOR, index.ordinal(), new int[]{color.x, color.y, color.z, color.w});
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearColorUnsigned(DrawBufferIndex index, Vector4i color) {
        assertBinding();
        GLX.glClearBufferuiv(GLX.GL_COLOR, index.ordinal(), new int[]{color.x, color.y, color.z, color.w});
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearDepth(float value) {
        assertBinding();
        GLX.glClearBufferfv(GLX.GL_DEPTH, 0, new float[]{value});
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer clearStencil(int value) {
        assertBinding();
        GLX.glClearBufferiv(GLX.GL_STENCIL, 0, new int[]{value});
        GLX.checkError();
        return this;
    }
}
