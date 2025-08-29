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
}
