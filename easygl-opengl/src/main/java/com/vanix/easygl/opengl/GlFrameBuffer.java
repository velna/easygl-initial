package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.core.AbstractHandle;
import com.vanix.easygl.core.graphics.*;
import org.joml.Vector4f;
import org.joml.Vector4i;

import java.util.function.IntConsumer;

public class GlFrameBuffer extends AbstractHandle implements FrameBuffer {
    public static final FrameBuffer DEFAULT_FRAME_BUFFER = new GlFrameBuffer(0);

    protected GlFrameBuffer() {
        this(GLX.glGenFramebuffers());
    }

    protected GlFrameBuffer(int handle) {
        super(handle, (IntConsumer) GLX::glDeleteFramebuffers);
    }

    @Override
    public FrameBuffer attach(Target target, Attachment attachment, Texture2D texture, int level) {
        GLX.glFramebufferTexture2D(target.value(), attachment.value(), texture.target().value(), texture.intHandle(), level);
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer attach(Target target, Attachment.Renderable attachment, RenderBuffer renderBuffer) {
        GLX.glFramebufferRenderbuffer(target.value(), attachment.value(), GLX.GL_RENDERBUFFER, renderBuffer.intHandle());
        GLX.checkError();
        return this;
    }

    @Override
    public FrameBuffer detachRenderBuffer(Target target, Attachment.Renderable attachment) {
        GLX.glFramebufferRenderbuffer(target.value(), attachment.value(), GLX.GL_RENDERBUFFER, 0);
        return this;
    }

    @Override
    public FrameBuffer clear(FrameBuffers attr) {
        GLX.glClear(attr.value());
        return this;
    }

    @Override
    public FrameBuffer setClearColor(float red, float blue, float green, float alpha) {
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
    public FrameBuffer setClearDepth(float depth) {
        GLX.glClearDepth(depth);
        return this;
    }

    @Override
    public float getClearDepth() {
        return GLX.glGetFloat(GLX.GL_DEPTH_CLEAR_VALUE);
    }

    @Override
    public FrameBuffer setClearStencil(int s) {
        GLX.glClearStencil(s);
        return this;
    }

    @Override
    public int getClearStencil() {
        return GLX.glGetInteger(GLX.GL_STENCIL_CLEAR_VALUE);
    }

    @Override
    public FrameBuffer selectDrawBuffer(ColorBuffer colorBuffer) {
        GLX.glDrawBuffer(colorBuffer.value());
        return this;
    }

    @Override
    public FrameBuffer selectDrawBuffers(ColorBuffer.MultiSelectable... drawBuffers) {
        int[] values = new int[drawBuffers.length];
        for (int i = 0; i < drawBuffers.length; i++) {
            values[i] = drawBuffers[i].value();
        }
        GLX.glDrawBuffers(values);
        return this;
    }

    @Override
    public FrameBuffer blit(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, FrameBuffers buffers, Texture.MagFilter filter) {
        GLX.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, buffers.value(), filter.value());
        return this;
    }

    @Override
    public FrameBuffer clearColor(int colorAttachmentIndex, Vector4f color) {
        GLX.glClearBufferfv(GLX.GL_COLOR, colorAttachmentIndex, new float[]{color.x, color.y, color.z, color.w});
        return this;
    }

    @Override
    public FrameBuffer clearColor(int colorAttachmentIndex, Vector4i color) {
        GLX.glClearBufferiv(GLX.GL_COLOR, colorAttachmentIndex, new int[]{color.x, color.y, color.z, color.w});
        return this;
    }

    @Override
    public FrameBuffer clearColorUnsigned(int colorAttachmentIndex, Vector4i color) {
        GLX.glClearBufferuiv(GLX.GL_COLOR, colorAttachmentIndex, new int[]{color.x, color.y, color.z, color.w});
        return this;
    }

    @Override
    public FrameBuffer clearDepth(float value) {
        GLX.glClearBufferfv(GLX.GL_DEPTH, 0, new float[]{value});
        return this;
    }

    @Override
    public FrameBuffer clearStencil(int value) {
        GLX.glClearBufferiv(GLX.GL_STENCIL, 0, new int[]{value});
        return this;
    }

    @Override
    public FrameBuffer clearDepthAndStencil(float depthValue, int stencilValue) {
        GLX.glClearBufferfi(GLX.GL_DEPTH_STENCIL, 0, depthValue, stencilValue);
        return this;
    }
}
