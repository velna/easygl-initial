package com.vanix.easygl.core.media;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.graphics.PixelFormat;
import com.vanix.easygl.core.meta.Meta;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.ByteBuffer;

public interface Image extends Closeable {
    Meta<Image> Meta = MetaSystem.Media.of(Image.class);

    PixelFormat format();

    int width();

    int height();

    ByteBuffer data();

    static Image load(String resourceFile) {
        return load(resourceFile, false, false);
    }

    static Image load(String resourceFile, boolean flipVertically) {
        return load(resourceFile, flipVertically, false);
    }

    static Image load(String resourceFile, boolean flipVertically, boolean unPremultiply) {
        return Meta.create(resourceFile, flipVertically, unPremultiply);
    }

    static Image empty(PixelFormat format, int width, int height) {
        return new SimpleImage(format, width, height, null);
    }
}
