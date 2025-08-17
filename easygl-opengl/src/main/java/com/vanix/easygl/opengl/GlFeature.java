package com.vanix.easygl.opengl;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.graphics.Graphics;

public abstract class GlFeature<T extends Feature<T, Graphics>> implements Feature<T, Graphics> {
    private final int capability;
    private final Graphics graphics;

    public GlFeature(int capability, Graphics graphics) {
        this.capability = capability;
        this.graphics = graphics;
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    @Override
    public boolean isEnabled() {
        return GLX.glIsEnabled(capability);
    }

    @Override
    public T enable() {
        GLX.glEnable(capability);
        return self();
    }

    @Override
    public T disable() {
        GLX.glDisable(capability);
        return self();
    }

    @Override
    public Graphics then() {
        return graphics;
    }
}
