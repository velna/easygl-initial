package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.*;
import org.lwjgl.opengl.GL;

public class GlGraphics implements Graphics {

    private final Depth depth = new GlDepth();
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
    public Graphics clear(BufferMask attr) {
        GLX.glClear(attr.value());
        return this;
    }

    @Override
    public Graphics clear(BufferMask attr1, BufferMask attr2) {
        GLX.glClear(attr1.value() | attr2.value());
        return this;
    }

    @Override
    public Graphics clear(BufferMask attr1, BufferMask attr2, BufferMask attr3) {
        GLX.glClear(attr1.value() | attr2.value() | attr3.value());
        return this;
    }

    @Override
    public Graphics clearColor(float red, float blue, float green, float alpha) {
        GLX.glClearColor(red, blue, green, alpha);
        return this;
    }

    @Override
    public Graphics polygonMode(PolygonFace face, PolygonMode mode) {
        GLX.glPolygonMode(face.value(), mode.value());
        return this;
    }

    @Override
    public Depth depth() {
        return depth;
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
    public FrameBuffer defaultFrameBuffer() {
        return GlFrameBuffer.DefaultFrameBuffer;
    }

    @Override
    public void close() {

    }
}
