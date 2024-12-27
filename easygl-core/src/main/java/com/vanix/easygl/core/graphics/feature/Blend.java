package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.core.graphics.gl.GLC;

public class Blend implements Feature<Blend> {

    public enum Factor {
        Zero(GLC.GL_ZERO),
        One(GLC.GL_ONE),
        SrcColor(GLC.GL_SRC_COLOR),
        OneMinusSrcColor(GLC.GL_ONE_MINUS_SRC_COLOR),
        DstColor(GLC.GL_DST_COLOR),
        OneMinusDstColor(GLC.GL_ONE_MINUS_DST_COLOR),
        SrcAlpha(GLC.GL_SRC_ALPHA),
        OneMinusSrcAlpha(GLC.GL_ONE_MINUS_SRC_ALPHA),
        DstAlpha(GLC.GL_DST_ALPHA),
        OneMinusDstAlpha(GLC.GL_ONE_MINUS_DST_ALPHA),
        ConstantColor(GLC.GL_CONSTANT_COLOR),
        OneMinusConstantColor(GLC.GL_ONE_MINUS_CONSTANT_COLOR),
        ConstantAlpha(GLC.GL_CONSTANT_ALPHA),
        OneMinusConstantAlpha(GLC.GL_ONE_MINUS_CONSTANT_ALPHA);
        private final int value;

        Factor(int value) {
            this.value = value;
        }
    }

    @Override
    public Blend enable() {
        GLC.glEnable(GLC.GL_BLEND);
        return this;
    }

    @Override
    public Blend disable() {
        GLC.glDisable(GLC.GL_BLEND);
        return this;
    }

    public Blend func(Factor src, Factor dst) {
        GLC.glBlendFunc(src.value, dst.value);
        return this;
    }
}
