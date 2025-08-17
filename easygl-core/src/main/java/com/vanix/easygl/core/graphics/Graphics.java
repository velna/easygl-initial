package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.MultiFeature;
import com.vanix.easygl.core.window.Window;
import org.joml.Vector2f;

import java.util.ServiceLoader;

public interface Graphics extends Closeable, MultiFeature<Capability, Graphics, Graphics> {

    Graphics viewPort(int x, int y, int width, int height);

    DepthTest depthTest();

    Blending blending();

    ScissorTest scissorTest();

    FrameBuffer defaultFrame();

    Graphics flush();

    Graphics releaseShaderCompiler();

    // region Multisampling
    Vector2f getMultiSample(int index);

    Graphics minSampleShading(float value);
    // endregion

    // region Points, Lines and Polygons
    Graphics setPointSize(float size);

    float getPointSize();

    Graphics setPointFadeThresholdSize(float size);

    float getPointFadeThresholdSize();

    Graphics setPointSpriteCoordOrigin(SpriteCoordOrigin spriteCoordOrigin);

    SpriteCoordOrigin getPointSpriteCoordOrigin();

    Graphics setLineWidth(float width);

    float getLineWidth();

    Graphics setFrontFaceDirection(FrontFaceDirection direction);

    FrontFaceDirection getFrontFaceDirection();

    Graphics setCullFaceMode(CullFaceMode mode);

    CullFaceMode getCullFaceMode();

    Graphics setPolygonMode(PolygonFace face, PolygonMode mode);

    PolygonMode[] getPolygonMode();

    Graphics setPolygonOffset(float factor, float units);

    float getPolygonOffsetFactor();

    float getPolygonOffsetUnits();

    default Vector2f getPolygonOffset() {
        return new Vector2f(getPolygonOffsetFactor(), getPolygonOffsetUnits());
    }
    // endregion

    static Graphics of() {
        return ServiceLoader.load(Graphics.class).findFirst().orElseThrow();
    }

    static Graphics of(Window window) {
        window.bind();
        return of().viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }

}
