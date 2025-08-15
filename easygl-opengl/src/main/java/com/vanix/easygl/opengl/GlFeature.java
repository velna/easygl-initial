package com.vanix.easygl.opengl;

import com.vanix.easygl.core.Feature;

public abstract class GlFeature<T extends Feature<T>> implements Feature<T> {
    private final int capability;

    public GlFeature(int capability) {
        this.capability = capability;
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
}
