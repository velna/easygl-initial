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

    // region View Ports
    Graphics viewport(int x, int y, int width, int height);

    Viewport viewport(int index);

    Graphics setDepthRange(double near, double far);

    @Support(since = Version.GL41)
    Graphics setDepthRange(float near, float far);

    @Support(since = Version.GL41)
    float[] getDepthRangeFloat();

    double[] getDepthRangeDouble();

    int[] getDepthRangeIntMapped();
    // endregion

    DefaultFrameBuffer defaultFrameBuffer();

    Graphics flush();

    Graphics awaitFinish();

    @Support(since = Version.GL41)
    Graphics releaseShaderCompiler();

    PixelStorageMode pixelStorageMode();

    Graphics provokingVertex(boolean first);

    @Support(since = Version.GL43)
    Graphics dispatchCompute(int numGroupsX, int numGroupsY, int numGroupsZ);

    @Support(since = Version.GL43)
    Graphics dispatchComputeIndirect(int numGroupsX, int numGroupsY, int numGroupsZ);

    @Support(since = Version.GL43)
    int getMaxComputeWorkGroupCountX();

    @Support(since = Version.GL43)
    int getMaxComputeWorkGroupCountY();

    @Support(since = Version.GL43)
    int getMaxComputeWorkGroupCountZ();

    Graphics hint(HintTarget target, HintMode mode);

    @Support(since = Version.GL45)
    ResetStatus getRestStatus();

    PrecisionFormat getVertexShaderPrecisionFormat(PrecisionFormat.Type type);

    PrecisionFormat getFragmentShaderPrecisionFormat(PrecisionFormat.Type type);

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
        return of().viewport(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }

}
