package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.BindableMeta;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.core.util.TypeReference;

public interface TextureCube extends Texture<TextureCube> {
    BindableMeta<Type<TextureCube>, TextureCube> Meta = MetaSystem.Graphics.of(TextureCube.class, new TypeReference<>() {
    });
    TextureCube load(Image[] images);
}
