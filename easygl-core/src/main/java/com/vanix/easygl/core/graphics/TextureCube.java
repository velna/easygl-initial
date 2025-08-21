package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.media.Image;

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
}
