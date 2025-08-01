package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.Blend;

public class GlBlend extends Blend {
    @Override
    public Blend enable() {
        GLX.glEnable(GLX.GL_BLEND);
        return this;
    }

    @Override
    public Blend disable() {
        GLX.glDisable(GLX.GL_BLEND);
        return this;
    }

    public Blend func(Factor src, Factor dst) {
        GLX.glBlendFunc(src.value(), dst.value());
        return this;
    }
}
