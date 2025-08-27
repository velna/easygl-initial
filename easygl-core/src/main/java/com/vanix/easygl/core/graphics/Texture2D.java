package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.media.Image;

public interface Texture2D extends Texture<Texture2D> {

    default Texture2D load(String imageResource, boolean flipVertically) {
        try (var image = Image.load(imageResource, flipVertically)) {
            return load(image);
        }
    }

    default Texture2D load(String imageResource) {
        return load(imageResource, false);
    }

    default Texture2D load(Image image) {
        return load(image, 0);
    }

    default Texture2D load(Image image, int level) {
        InternalPixelFormat internalPixelFormat = image.format().internalPixelFormat();
        if (internalPixelFormat == null) {
            throw new IllegalArgumentException();
        }
        return load(image, level, internalPixelFormat);
    }

    Texture2D load(Image image, int level, InternalPixelFormat internalFormat);

}
