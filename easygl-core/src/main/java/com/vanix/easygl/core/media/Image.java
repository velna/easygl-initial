package com.vanix.easygl.core.media;

import com.vanix.easygl.core.graphics.PixelFormat;
import com.vanix.easygl.core.meta.MetaSystem;

import java.io.Closeable;
import java.nio.ByteBuffer;

public interface Image extends Closeable {
    static Image load(String resourceFile) {
        return MetaSystem.Media.of(Image.class, resourceFile);
    }

    PixelFormat format();

    int width();

    int height();

    ByteBuffer data();
}
