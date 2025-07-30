package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.AbstractBindable;
import com.vanix.easygl.core.graphics.BindTarget;
import com.vanix.easygl.core.graphics.RenderBuffer;

public class GlRenderBuffer extends AbstractBindable<BindTarget.Default<RenderBuffer>, RenderBuffer> implements RenderBuffer {
    public GlRenderBuffer() {
        super(GLC.glGenRenderbuffers(), Target);
    }

    @Override
    protected void close(int handle) {
        GLC.glDeleteRenderbuffers(handle);
    }

    @Override
    public RenderBuffer storage(Format internalFormat, int width, int height) {
        assertBinding();
        GLC.glRenderbufferStorage(GLC.GL_RENDERBUFFER, internalFormat.value(), width, height);
        GLC.checkError();
        return this;
    }

    @Override
    public RenderBuffer storageMultiSample(int samples, Format internalFormat, int width, int height) {
        assertBinding();
        GLC.glRenderbufferStorageMultisample(GLC.GL_RENDERBUFFER, samples, internalFormat.value(), width, height);
        GLC.checkError();
        return this;
    }
}
