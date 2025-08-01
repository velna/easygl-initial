package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.CullFace;

public class GlCullFace extends CullFace {
    public CullFace mode(Mode mode) {
        GLX.glCullFace(mode.value());
        return this;
    }

    @Override
    public CullFace enable() {
        GLX.glEnable(GLX.GL_CULL_FACE);
        return this;
    }

    @Override
    public CullFace disable() {
        GLX.glDisable(GLX.GL_CULL_FACE);
        return this;
    }
}
