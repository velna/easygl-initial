package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.Image;
import com.vanix.easygl.core.graphics.TextureCube;

public class GlTextureCube extends AbstractTexture<TextureCube> implements TextureCube {
    public GlTextureCube(String id) {
        super(id, Type.CubeMap);
    }

    public TextureCube load(Image[] images) {
        if (null == images || images.length != 6) {
            throw new IllegalArgumentException("images must be size of 6.");
        }
        assertBinding();
        for (int i = 0; i < 6; i++) {
            Image image = images[i];
            GLC.glTexImage2D(GLC.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i,
                    0,
                    image.format().value(),
                    image.width(),
                    image.height(),
                    0,
                    image.format().value(),
                    GLC.GL_UNSIGNED_BYTE,
                    image.data());
            GLC.checkError();
        }
        return this;
    }

}
