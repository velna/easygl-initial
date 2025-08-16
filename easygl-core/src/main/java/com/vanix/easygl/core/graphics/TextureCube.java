package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.media.Image;

public interface TextureCube extends Texture<TextureCube> {

    TextureCube load(Image[] images);
}
