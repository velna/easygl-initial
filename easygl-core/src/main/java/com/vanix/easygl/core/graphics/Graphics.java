package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.window.Window;
import org.joml.Vector2f;

import java.util.ServiceLoader;

public interface Graphics extends Closeable {

    Graphics viewPort(int x, int y, int width, int height);

    DepthTest depthTest();

    CullFace cullFace();

    Blend blend();

    Graphics polygonMode(PolygonFace face, PolygonMode mode);

    FrameBuffer defaultFrame();

    Graphics flush();

    Graphics releaseShaderCompiler();

    Vector2f getMultiSample(int index);

    Graphics minSampleShading(float value);

    Graphics pointSize(float size);

    Graphics pointFadeThresholdSize(float size);

    Graphics pointSpriteCoordOrigin(SpriteCoordOrigin spriteCoordOrigin);

    Graphics lineWidth(float width);

    Graphics frontFaceClockwise();

    Graphics frontFaceCounterclockwise();

    Graphics polygonOffset(float factor, float units);

    static Graphics of() {
        return ServiceLoader.load(Graphics.class).findFirst().orElseThrow();
    }

    static Graphics of(Window window) {
        window.bind();
        return of().viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }
}
