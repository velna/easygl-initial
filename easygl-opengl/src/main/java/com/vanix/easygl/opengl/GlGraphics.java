package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.*;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL;

public class GlGraphics implements Graphics {

    private final DepthTest depthTest = new GlDepthTest();
    private final CullFace cullFace = new GlCullFace();

    private final Blend blend = new GlBlend();

    public GlGraphics() {
        GL.createCapabilities();
    }

    @Override
    public Graphics viewPort(int x, int y, int width, int height) {
        GLX.glViewport(x, y, width, height);
        return this;
    }

    @Override
    public Graphics polygonMode(PolygonFace face, PolygonMode mode) {
        GLX.glPolygonMode(face.value(), mode.value());
        return this;
    }

    @Override
    public DepthTest depthTest() {
        return depthTest;
    }

    @Override
    public CullFace cullFace() {
        return cullFace;
    }

    @Override
    public Blend blend() {
        return blend;
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
    public Graphics pointSize(float size) {
        GLX.glPointSize(size);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics pointFadeThresholdSize(float size) {
        GLX.glPointParameterf(GLX.GL_POINT_FADE_THRESHOLD_SIZE, size);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics pointSpriteCoordOrigin(SpriteCoordOrigin spriteCoordOrigin) {
        GLX.glPointParameteri(GLX.GL_POINT_SPRITE_COORD_ORIGIN, spriteCoordOrigin.value());
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics lineWidth(float width) {
        GLX.glLineWidth(width);
        GLX.checkError();
        return this;
    }

    @Override
    public Graphics frontFaceClockwise() {
        GLX.glFrontFace(GLX.GL_CW);
        return this;
    }

    @Override
    public Graphics frontFaceCounterclockwise() {
        GLX.glFrontFace(GLX.GL_CCW);
        return this;
    }

    @Override
    public Graphics polygonOffset(float factor, float units) {
        GLX.glPolygonOffset(factor, units);
        GLX.checkError();
        return this;
    }

    @Override
    public void close() {

    }
}
