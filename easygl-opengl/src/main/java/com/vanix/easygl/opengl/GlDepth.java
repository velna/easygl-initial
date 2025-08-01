package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.Depth;

public class GlDepth extends Depth {

    public Depth func(Functions function) {
        GLX.glDepthFunc(function.value());
        return this;
    }

    @Override
    public Depth enable() {
        enable(Capability.DepthTest);
        return this;
    }

    @Override
    public Depth disable() {
        disable(Capability.DepthTest);
        return this;
    }

    @Override
    public Depth enable(Capability feature) {
        GLX.glEnable(feature.value());
        return this;
    }

    @Override
    public Depth disable(Capability feature) {
        GLX.glDisable(feature.value());
        return this;
    }
}
