package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.core.graphics.gl.GLC;

public class Depth implements MultiFeature<Depth.Capability, Depth> {

    public enum Capability {
        DepthTest(GLC.GL_DEPTH_TEST),
        DepthClamp(GLC.GL_DEPTH_CLAMP);
        private final int value;

        Capability(int value) {
            this.value = value;
        }

    }

    public enum Functions {
        Never(GLC.GL_NEVER),
        LT(GLC.GL_LESS),
        EQ(GLC.GL_EQUAL),
        LE(GLC.GL_LEQUAL),
        GT(GLC.GL_GREATER),
        NE(GLC.GL_NOTEQUAL),
        GE(GLC.GL_GEQUAL),
        ALWAYS(GLC.GL_ALWAYS);
        private final int value;

        Functions(int value) {
            this.value = value;
        }
    }

    public Depth func(Functions function) {
        GLC.glDepthFunc(function.value);
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
        GLC.glEnable(feature.value);
        return this;
    }

    @Override
    public Depth disable(Capability feature) {
        GLC.glDisable(feature.value);
        return this;
    }
}
