package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.GraphicsException;
import com.vanix.easygl.core.graphics.Image;
import com.vanix.easygl.core.graphics.Texture;
import com.vanix.easygl.core.graphics.Texture2D;

public class GlTexture2D extends AbstractTexture<Texture2D> implements Texture2D {

    public GlTexture2D(String id) {
        super(id, Texture.Type.T2D);
    }

    @Override
    public Texture2D load(Image image, int level) throws GraphicsException {
        assertBinding();
        GLC.glTexImage2D(target().value(),
                level,
                image.format().value(),
                image.width(),
                image.height(),
                0,
                image.format().value(),
                GLC.GL_UNSIGNED_BYTE,
                image.data());
        GLC.checkError();
        return this;
    }

}
