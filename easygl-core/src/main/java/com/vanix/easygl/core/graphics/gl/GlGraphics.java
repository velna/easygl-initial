package com.vanix.easygl.core.graphics.gl;

import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.core.graphics.PolygonFace;
import com.vanix.easygl.core.graphics.PolygonMode;
import com.vanix.easygl.core.graphics.feature.Blend;
import com.vanix.easygl.core.graphics.feature.CullFace;
import com.vanix.easygl.core.graphics.feature.Depth;

public class GlGraphics implements Graphics {

    private final Depth depth = new Depth();
    private final CullFace cullFace = new CullFace();

    private final Blend blend = new Blend();

    public GlGraphics() {
    }

    @Override
    public Graphics viewPort(int x, int y, int width, int height) {
        GLC.glViewport(x, y, width, height);
        return this;
    }

    @Override
    public Graphics clear(BufferMask attr) {
        GLC.glClear(attr.value());
        return this;
    }

    @Override
    public Graphics clear(BufferMask attr1, BufferMask attr2) {
        GLC.glClear(attr1.value() | attr2.value());
        return this;
    }

    @Override
    public Graphics clear(BufferMask attr1, BufferMask attr2, BufferMask attr3) {
        GLC.glClear(attr1.value() | attr2.value() | attr3.value());
        return this;
    }

    @Override
    public Graphics clearColor(float red, float blue, float green, float alpha) {
        GLC.glClearColor(red, blue, green, alpha);
        return this;
    }

    @Override
    public Graphics polygonMode(PolygonFace face, PolygonMode mode) {
        GLC.glPolygonMode(face.value(), mode.value());
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
}
