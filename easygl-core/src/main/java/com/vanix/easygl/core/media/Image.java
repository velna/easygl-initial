package com.vanix.easygl.core.media;

import com.vanix.easygl.core.graphics.PixelFormat;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.commons.util.TypeReference;

import java.io.Closeable;
import java.nio.ByteBuffer;

public interface Image extends Closeable {
    TypeReference<Image> TYPE_REF = new TypeReference<>() {
    };

    static Image load(String resourceFile) {
        return MetaSystem.Media.of(Image.class, TYPE_REF, resourceFile);
    }

    PixelFormat format();

    int width();

    int height();

    ByteBuffer data();
}
