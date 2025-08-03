package com.vanix.easygl.core.media;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.graphics.PixelFormat;
import com.vanix.easygl.core.meta.Meta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.ByteBuffer;

public interface Image extends Closeable {
    Meta<Image> Meta = MetaSystem.Media.of(Image.class);

    static Image load(String resourceFile) {
        return Meta.create(resourceFile);
    }

    PixelFormat format();

    int width();

    int height();

    ByteBuffer data();
}
