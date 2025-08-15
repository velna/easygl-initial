package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.CullFace;

public class GlCullFace extends GlFeature<CullFace> implements CullFace {
    public GlCullFace() {
        super(GLX.GL_CULL_FACE);
    }

    public CullFace mode(Mode mode) {
        GLX.glCullFace(mode.value());
        return this;
    }

    @Override
    public CullFace frontClockwise() {
        GLX.glFrontFace(GLX.GL_CW);
        return this;
    }

    @Override
    public CullFace frontCounterclockwise() {
        GLX.glFrontFace(GLX.GL_CCW);
        return this;
    }

    @Override
    public boolean isFrontClockwise() {
        return GLX.glGetInteger(GLX.GL_FRONT_FACE) == GLX.GL_CW;
    }

    @Override
    public boolean isFrontCounterclockwise() {
        return GLX.glGetInteger(GLX.GL_FRONT_FACE) == GLX.GL_CCW;
    }
}
