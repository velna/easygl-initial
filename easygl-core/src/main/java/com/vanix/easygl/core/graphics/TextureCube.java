package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.media.Image;
import com.vanix.easygl.core.meta.MetaSystem;

public interface TextureCube extends Texture<TextureCube> {

    default TextureCube load(String... resources) {
        Image[] images = new Image[resources.length];
        try {
            for (int i = 0; i < resources.length; i++) {
                images[i] = Image.load(resources[i]);
            }
            return load(images);
        } finally {
            for (var image : images) {
                if (image != null) {
                    image.close();
                }
            }
        }
    }


    TextureCube load(Image... images);

    enum Face implements IntEnum {
        PositiveX("TEXTURE_CUBE_MAP_POSITIVE_X"),
        NegativeX("TEXTURE_CUBE_MAP_NEGATIVE_X"),
        PositiveY("TEXTURE_CUBE_MAP_POSITIVE_Y"),
        NegativeY("TEXTURE_CUBE_MAP_NEGATIVE_Y"),
        PositiveZ("TEXTURE_CUBE_MAP_POSITIVE_Z"),
        NegativeZ("TEXTURE_CUBE_MAP_NEGATIVE_Z");
        private final int value;

        Face(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }
}
