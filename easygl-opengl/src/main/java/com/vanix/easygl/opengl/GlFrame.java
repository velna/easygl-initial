package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;
import org.joml.Vector4i;

import java.util.function.IntConsumer;

public class GlFrame extends AbstractHandle implements Frame, GlFrameOps<Frame> {
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
