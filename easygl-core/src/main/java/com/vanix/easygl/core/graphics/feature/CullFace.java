package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.core.graphics.gl.GLC;

public class CullFace implements Feature<CullFace> {
    public enum Mode {
        Front(GLC.GL_FRONT),
        Back(GLC.GL_BACK),
        FrontAndBack(GLC.GL_FRONT_AND_BACK);
        private final int value;

        Mode(int value) {
            this.value = value;
        }
    }

    public CullFace mode(Mode mode) {
        GLC.glCullFace(mode.value);
        return this;
    }

    @Override
    public CullFace enable() {
        GLC.glEnable(GLC.GL_CULL_FACE);
        return this;
    }

    @Override
    public CullFace disable() {
        GLC.glDisable(GLC.GL_CULL_FACE);
        return this;
    }
}
