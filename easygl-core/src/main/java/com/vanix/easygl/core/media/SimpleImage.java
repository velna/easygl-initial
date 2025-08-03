package com.vanix.easygl.core.media;

import com.vanix.easygl.core.graphics.PixelFormat;

import java.nio.ByteBuffer;

public record SimpleImage(PixelFormat format, int width, int height, ByteBuffer data) implements Image {
    @Override
    public void close() {
        data.clear();
    }
}
