package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.InternalPixelFormat;
import com.vanix.easygl.core.graphics.TextureMultiSample;

public class GlTextureMultiSample extends AbstractTexture<TextureMultiSample> implements TextureMultiSample {
    public GlTextureMultiSample(int handle) {
        super(handle);
        target = Target.T2DMultiSample;
    }

    public GlTextureMultiSample() {
        this(GLX.glGenTextures());
    }

    @Override
    public TextureMultiSample establish(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations) {
        GLX.glTexImage2DMultisample(GLX.GL_TEXTURE_2D_MULTISAMPLE, samples, pixelFormat.value(), width, height, fixedSampleLocations);
        GLX.checkError();
        return this;
    }

    @Override
    public TextureMultiSample establishProxy(int samples, InternalPixelFormat pixelFormat, int width, int height, boolean fixedSampleLocations) {
        GLX.glTexImage2DMultisample(GLX.GL_PROXY_TEXTURE_2D_MULTISAMPLE, samples, pixelFormat.value(), width, height, fixedSampleLocations);
        GLX.checkError();
        return this;
    }
}
