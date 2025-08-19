package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.TextureCube;
import com.vanix.easygl.core.media.Image;

public class GlTextureCube extends AbstractTexture<TextureCube> implements TextureCube {

    protected GlTextureCube() {
        this(GLX.glGenTextures());
    }

    protected GlTextureCube(int handle) {
        super(handle);
    }

    public TextureCube load(Image[] images) {
        if (null == images || images.length != 6) {
            throw new IllegalArgumentException("images must be size of 6.");
        }
        assertBinding();
        for (int i = 0; i < 6; i++) {
            Image image = images[i];
            GLX.glTexImage2D(GLX.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i,
                    0,
                    image.format().value(),
                    image.width(),
                    image.height(),
                    0,
                    image.format().value(),
                    GLX.GL_UNSIGNED_BYTE,
                    image.data());
            GLX.checkError();
        }
        return this;
    }

}
