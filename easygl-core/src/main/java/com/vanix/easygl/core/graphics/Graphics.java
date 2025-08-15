package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.window.Window;

import java.util.ServiceLoader;

public interface Graphics extends FrameBufferOps<Graphics>, Closeable {

    Graphics viewPort(int x, int y, int width, int height);

    DepthTest depthTest();

    CullFace cullFace();

    Blend blend();

    Graphics polygonMode(PolygonFace face, PolygonMode mode);

    FrameBuffer defaultFrameBuffer();

    static Graphics of() {
        return ServiceLoader.load(Graphics.class).findFirst().orElseThrow();
    }

    static Graphics of(Window window) {
        window.bind();
        return of().viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }
}
