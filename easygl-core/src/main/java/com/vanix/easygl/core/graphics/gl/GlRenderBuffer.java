package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.AbstractBindableHandle;
import com.vanix.easygl.core.graphics.RenderBuffer;

public class GlRenderBuffer extends AbstractBindableHandle<RenderBuffer> implements RenderBuffer {
    public GlRenderBuffer() {
        super(GLC.glGenRenderbuffers(), State);
    }

    @Override
    protected void bind(int handle) {
        GLC.glBindRenderbuffer(GLC.GL_RENDERBUFFER, handle);
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
