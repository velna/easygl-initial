package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.InternalPixelFormat;
import com.vanix.easygl.core.graphics.Texture;
import com.vanix.easygl.core.graphics.Texture2D;
import com.vanix.easygl.core.media.Image;

public class GlTexture2D extends AbstractTexture<Texture2D> implements Texture2D {

    protected GlTexture2D(Object... args) {
        this(GLX.glGenTextures(), args);
    }

    protected GlTexture2D(int handle, Object... args) {
        super(handle, Texture.Type.T2D);
    }

    @Override
    public Texture2D load(Image image, int level, InternalPixelFormat internalFormat) throws GraphicsException {
        assertBinding();
        GLX.glTexImage2D(target().value(),
                level,
                internalFormat.value(),
                image.width(),
                image.height(),
                0,
                image.format().value(),
                GLX.GL_UNSIGNED_BYTE,
                image.data());
        GLX.checkError();
        return this;
    }

}
