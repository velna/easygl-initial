package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.Image;
import com.vanix.easygl.core.graphics.Texture;
import com.vanix.easygl.core.graphics.TextureCube;

public class GlTextureCube extends AbstractTexture<TextureCube> implements TextureCube {

    protected GlTextureCube(Object... args) {
        this(GLX.glGenTextures(), args);
    }

    protected GlTextureCube(int handle, Object... args) {
        super(handle, (String) args[0], Texture.Type.CubeMap);
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
