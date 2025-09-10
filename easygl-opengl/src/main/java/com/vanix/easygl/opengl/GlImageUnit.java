package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.Access;
import com.vanix.easygl.core.graphics.ImageUnit;
import com.vanix.easygl.core.graphics.InternalPixelFormat;
import com.vanix.easygl.core.graphics.Texture;

public class GlImageUnit extends ImageUnit {
    public GlImageUnit(int index) {
        super(index);
    }

    @Override
    public ImageUnit bindTexture(Texture<?> texture, int level, boolean layered, int layer, Access access, InternalPixelFormat.Sized format) {
        GLX.glBindImageTexture(value, texture.intHandle(), level, layered, layer, access.value(), format.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Texture<?> bindingTexture() {
        return AbstractTexture.get(GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_NAME, value));
    }

    @Override
    public int bindingLevel() {
        return GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_LEVEL, value);
    }

    @Override
    public boolean isBindingLayered() {
        return GLX.glGetBooleani(GLX.GL_IMAGE_BINDING_LAYERED, value);
    }

    @Override
    public Access bindingAccess() {
        return Cache.Access.valueOf(GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_ACCESS, value));
    }

    @Override
    public InternalPixelFormat.Sized bindingFormat() {
        return (InternalPixelFormat.Sized) Cache.InternalPixelFormatCache.get(GLX.glGetIntegeri(GLX.GL_IMAGE_BINDING_FORMAT, value));
    }
}
