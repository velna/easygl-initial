package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.commons.util.TypeReference;

public interface Texture2D extends Texture<Texture2D> {
    BindableMeta<Type<Texture2D>, Texture2D> Meta = MetaSystem.Graphics.of(Texture2D.class, new TypeReference<>() {
    });

    default Texture2D load(Image image) throws GraphicsException {
        return load(image, 0);
    }

    Texture2D load(Image image, int level) throws GraphicsException;

}
