package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Texture2D extends Texture<Texture2D> {
    BindableMeta<Type<Texture2D>, Texture2D> Meta = MetaSystem.Graphics.of(Texture2D.class);

    default Texture2D load(String imageResource) {
        try (var image = Image.load(imageResource)) {
            return load(image);
        }
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
