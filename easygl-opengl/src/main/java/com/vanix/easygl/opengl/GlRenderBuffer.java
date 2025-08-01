package com.vanix.easygl.opengl;

import com.vanix.easygl.core.AbstractBindable;
import com.vanix.easygl.core.BindTarget;
import com.vanix.easygl.core.graphics.RenderBuffer;

import java.util.function.IntConsumer;

public class GlRenderBuffer extends AbstractBindable<BindTarget.Default<RenderBuffer>, RenderBuffer> implements RenderBuffer {

    protected static final BindTarget.Default<RenderBuffer> Target = new BindTarget.Default<>(
            GLX.GL_RENDERBUFFER, "RenderBuffer", GlMetaService.RenderBufferMeta);

    private GlRenderBuffer(int handle) {
        super(handle, Target, (IntConsumer) GLX::glDeleteRenderbuffers);
    }

    protected GlRenderBuffer(Object... args) {
        this(GLX.glGenRenderbuffers(), args);
    }

    protected GlRenderBuffer(int handle, Object... args) {
        this(handle);
    }


    @Override
    public RenderBuffer storage(Format internalFormat, int width, int height) {
        assertBinding();
        GLX.glRenderbufferStorage(GLX.GL_RENDERBUFFER, internalFormat.value(), width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public RenderBuffer storageMultiSample(int samples, Format internalFormat, int width, int height) {
        assertBinding();
        GLX.glRenderbufferStorageMultisample(GLX.GL_RENDERBUFFER, samples, internalFormat.value(), width, height);
        GLX.checkError();
        return this;
    }
}
