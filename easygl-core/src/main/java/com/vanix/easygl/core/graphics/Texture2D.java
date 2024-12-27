package com.vanix.easygl.core.graphics;

public interface Texture2D extends Texture<Texture2D> {

    default Texture2D load(Image image) throws GraphicsException{
        return load(image, 0);
    }

    Texture2D load(Image image, int level) throws GraphicsException;

}
