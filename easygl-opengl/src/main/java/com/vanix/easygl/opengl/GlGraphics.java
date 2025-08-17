package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.util.IntEnumCache;
import com.vanix.easygl.core.graphics.*;
import org.joml.Vector2f;

public class GlGraphics implements Graphics {
    private static final IntEnumCache<PolygonMode> PolygonModeCache = new IntEnumCache<>(PolygonMode.class, 0xf);

    private final DepthTest depthTest = new GlDepthTest(this);
    private final Blending blending = new GlBlending(this);
    private final ScissorTest scissorTest = new GlScissorTest(this);

    @Override
    public Graphics viewPort(int x, int y, int width, int height) {
        GLX.glViewport(x, y, width, height);
        return this;
    }

    @Override
    public DepthTest depthTest() {
        return depthTest;
    }

    @Override
    public Blending blending() {
        return blending;
    }

    @Override
    public ScissorTest scissorTest() {
        return scissorTest;
    }

    @Override
    public FrameBuffer defaultFrame() {
        return GlFrameBuffer.DEFAULT_FRAME_BUFFER;
    }

    @Override
    public Graphics flush() {
        GLX.glFlush();
        return this;
    }

    @Override
    public Graphics releaseShaderCompiler() {
        GLX.glReleaseShaderCompiler();
        return this;
    }

    @Override
    public Vector2f getMultiSample(int index) {
        float[] data = new float[2];
        GLX.glGetMultisamplefv(GLX.GL_SAMPLE_POSITION, index, data);
        GLX.checkError();
        return new Vector2f(data);
    }

    @Override
    public Graphics minSampleShading(float value) {
        GLX.glMinSampleShading(value);
        return this;
    }

    @Override
    public Graphics setPointSize(float size) {
        GLX.glPointSize(size);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setPointFadeThresholdSize(float size) {
        GLX.glPointParameterf(GLX.GL_POINT_FADE_THRESHOLD_SIZE, size);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setPointSpriteCoordOrigin(SpriteCoordOrigin spriteCoordOrigin) {
        GLX.glPointParameteri(GLX.GL_POINT_SPRITE_COORD_ORIGIN, spriteCoordOrigin.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setLineWidth(float width) {
        GLX.glLineWidth(width);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setFrontFaceDirection(FrontFaceDirection direction) {
        GLX.glFrontFace(direction.value());
        return this;
    }

    @Override
    public Graphics setPolygonOffset(float factor, float units) {
        GLX.glPolygonOffset(factor, units);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics setPolygonMode(PolygonFace face, PolygonMode mode) {
        GLX.glPolygonMode(face.value(), mode.value());
        return this;
    }

    @Override
    public float getPointSize() {
        return GLX.glGetFloat(GLX.GL_POINT_SIZE);
    }

    @Override
    public float getPointFadeThresholdSize() {
        return GLX.glGetFloat(GLX.GL_POINT_FADE_THRESHOLD_SIZE);
    }

    @Override
    public SpriteCoordOrigin getPointSpriteCoordOrigin() {
        return switch (GLX.glGetInteger(GLX.GL_POINT_SPRITE_COORD_ORIGIN)) {
            case GLX.GL_LOWER_LEFT -> SpriteCoordOrigin.LowerLeft;
            case GLX.GL_UPPER_LEFT -> SpriteCoordOrigin.UpperLeft;
            default -> {
                GLX.checkError();
                yield null;
            }
        };
    }

    @Override
    public float getLineWidth() {
        return GLX.glGetFloat(GLX.GL_LINE_WIDTH);
    }

    @Override
    public FrontFaceDirection getFrontFaceDirection() {
        return switch (GLX.glGetInteger(GLX.GL_FRONT_FACE)) {
            case GLX.GL_CW -> FrontFaceDirection.Clockwise;
            case GLX.GL_CCW -> FrontFaceDirection.Counterclockwise;
            default -> {
                GLX.checkError();
                yield null;
            }
        };
    }

    @Override
    public Graphics setCullFaceMode(CullFaceMode mode) {
        GLX.glCullFace(mode.value());
        return this;
    }

    @Override
    public CullFaceMode getCullFaceMode() {
        return switch (GLX.glGetInteger(GLX.GL_CULL_FACE_MODE)) {
            case GLX.GL_FRONT -> CullFaceMode.Front;
            case GLX.GL_BACK -> CullFaceMode.Back;
            case GLX.GL_FRONT_AND_BACK -> CullFaceMode.FrontAndBack;
            default -> {
                GLX.checkError();
                yield null;
            }
        };
    }

    @Override
    public PolygonMode[] getPolygonMode() {
        int[] values = new int[2];
        GLX.glGetIntegerv(GLX.GL_POLYGON_MODE, values);
        GLX.checkError();
        return new PolygonMode[]{PolygonModeCache.valueOf(values[0]), PolygonModeCache.valueOf(values[1])};
    }

    @Override
    public float getPolygonOffsetFactor() {
        return GLX.glGetFloat(GLX.GL_POLYGON_OFFSET_FACTOR);
    }

    @Override
    public float getPolygonOffsetUnits() {
        return GLX.glGetFloat(GLX.GL_POLYGON_OFFSET_UNITS);
    }

    @Override
    public Graphics enable(Capability feature) {
        GLX.glEnable(feature.value());
        return this;
    }

    @Override
    public Graphics disable(Capability feature) {
        GLX.glDisable(feature.value());
        return this;
    }

    @Override
    public boolean isEnable(Capability feature) {
        return GLX.glIsEnabled(feature.value());
    }

    @Override
    public Graphics then() {
        return this;
    }

    @Override
    public void close() {

    }
}
