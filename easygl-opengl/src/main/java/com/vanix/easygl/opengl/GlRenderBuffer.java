package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.InternalPixelFormat;
import com.vanix.easygl.core.graphics.RenderBuffer;

import java.util.function.IntConsumer;

public class GlRenderBuffer extends AbstractBindable<BindTarget.Default<RenderBuffer>, RenderBuffer> implements RenderBuffer {

    protected GlRenderBuffer() {
        this(GLX.glGenRenderbuffers());
    }

    protected GlRenderBuffer(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteRenderbuffers);
    }

    @Override
    public RenderBuffer storage(InternalPixelFormat internalFormat, int width, int height) {
        assertBinding();
        GLX.glRenderbufferStorage(GLX.GL_RENDERBUFFER, internalFormat.value(), width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public RenderBuffer storageMultiSample(int samples, InternalPixelFormat internalFormat, int width, int height) {
        assertBinding();
        GLX.glRenderbufferStorageMultisample(GLX.GL_RENDERBUFFER, samples, internalFormat.value(), width, height);
        GLX.checkError();
        return this;
    }
}
