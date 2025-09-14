package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.FrameBuffer;
import com.vanix.easygl.core.graphics.FrameInnerBuffer;

public class GlFrameBuffer extends GlBaseFrameBuffer<FrameBuffer> implements FrameBuffer {

    protected GlFrameBuffer() {
        this(GLX.glGenFramebuffers());
    }

    protected GlFrameBuffer(int handle) {
        super(handle, GLX::glDeleteFramebuffers);
    }

    @Override
    public FrameBuffer selectDrawBuffer(FrameInnerBuffer.ColorBuffer colorBuffer) {
        GLX.glDrawBuffer(colorBuffer.value());
        return this;
    }

    @Override
    public FrameBuffer invalidate(Target<FrameBuffer> target, int x, int y, int width, int height, FrameInnerBuffer.Attachment attachment) {
        assertBinding();
        GLX.glInvalidateSubFramebuffer(target.value(), attachment.value(), x, y, width, height);
        return this;
    }

    @Override
    public FrameBuffer setDefaultWidth(int width) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_WIDTH, width);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultWidth() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_WIDTH);
    }

    @Override
    public FrameBuffer setDefaultHeight(int height) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_HEIGHT, height);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultHeight() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_HEIGHT);
    }

    @Override
    public FrameBuffer setDefaultLayers(int layers) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_LAYERS, layers);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultLayers() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_LAYERS);
    }

    @Override
    public FrameBuffer setDefaultSamples(int samples) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_SAMPLES, samples);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultSamples() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_SAMPLES);
    }

    @Override
    public FrameBuffer setDefaultFixedSampleLocations(int fixedSampleLocations) {
        assertBinding();
        GLX.glFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS, fixedSampleLocations);
        GLX.checkError();
        return this;
    }

    @Override
    public int getDefaultFixedSampleLocations() {
        assertBinding();
        return GLX.glGetFramebufferParameteri(target.value(), GLX.GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS);
    }
}
