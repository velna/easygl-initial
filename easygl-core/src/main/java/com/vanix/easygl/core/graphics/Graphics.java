package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Closeable;
import com.vanix.easygl.core.MultiFeature;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.feature.*;
import com.vanix.easygl.core.window.Window;
import org.joml.Vector2f;

import java.nio.FloatBuffer;
import java.util.ServiceLoader;

public interface Graphics extends Closeable, MultiFeature<Capability, Graphics, Graphics> {

    Graphics viewPort(int x, int y, int width, int height);

    DefaultFrameBuffer defaultFrameBuffer();

    Graphics flush();

    @Support(since = Version.GL41)
    Graphics releaseShaderCompiler();

    PixelStorageMode pixelStorageMode();

    Graphics provokingVertex(boolean first);

    //region Features
    Clipping clipDistances();

    DepthTest depthTest();

    Blending blending();

    StencilTest stencilTest();

    ScissorTest scissorTest();

    LogicalOperation logicalOperation();

    @Support(since = Version.GL43)
    Debug debug();

    @Support(since = Version.GL31)
    PrimitiveRestart primitiveRestart();
    //endregion

    // region Multisampling
    Vector2f getMultiSample(int index);

    Graphics minSampleShading(float value);
    // endregion

    // region Points, Lines and Polygons
    Graphics setPointSize(float size);

    float getPointSize();

    Graphics setPointFadeThresholdSize(float size);

    float getPointFadeThresholdSize();

    Graphics setPointSpriteCoordOrigin(Origin origin);

    Origin getPointSpriteCoordOrigin();

    Graphics setLineWidth(float width);

    float getLineWidth();

    Graphics setFrontFaceDirection(FrontFaceDirection direction);

    FrontFaceDirection getFrontFaceDirection();

    Graphics setCullFaceMode(CullFaceMode mode);

    CullFaceMode getCullFaceMode();

    Graphics setPolygonMode(Face face, PolygonMode mode);

    PolygonMode[] getPolygonMode();

    Graphics setPolygonOffset(float factor, float units);

    float getPolygonOffsetFactor();

    float getPolygonOffsetUnits();

    default Vector2f getPolygonOffset() {
        return new Vector2f(getPolygonOffsetFactor(), getPolygonOffsetUnits());
    }
    // endregion

    // region Path Parameters
    Graphics setPathVertices(int count);

    Graphics setPathDefaultOuterLevel(float[] values);

    Graphics setPathDefaultOuterLevel(FloatBuffer value);

    Graphics setPathDefaultInnerLevel(float[] values);

    Graphics setPathDefaultInnerLevel(FloatBuffer value);
    // endregion

    static Graphics of() {
        return ServiceLoader.load(Graphics.class).findFirst().orElseThrow();
    }

    static Graphics of(Window window) {
        window.bind();
        return of().viewPort(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }

}
