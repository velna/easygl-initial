package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Image;
import com.vanix.easygl.core.graphics.Texture;
import com.vanix.easygl.core.graphics.Texture2D;

public class GlTexture2D extends AbstractTexture<Texture2D> implements Texture2D {

    protected GlTexture2D(Object... args) {
        this(GLX.glGenTextures(), args);
    }

    protected GlTexture2D(int handle, Object... args) {
        super(handle, (String) args[0], Texture.Type.T2D);
    }

    @Override
    public Texture2D load(Image image, int level) throws GraphicsException {
        assertBinding();
        GLX.glTexImage2D(target().value(),
                level,
                image.format().value(),
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
