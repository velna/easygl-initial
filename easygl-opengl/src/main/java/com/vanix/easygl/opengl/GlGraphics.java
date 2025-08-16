package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.*;
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
    public void close() {

    }
}
