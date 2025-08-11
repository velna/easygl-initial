package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class Blend implements Feature<Blend> {

    public enum Factor {
        Zero(MetaSystem.Graphics.queryInt("ZERO")),
        One(MetaSystem.Graphics.queryInt("ONE")),
        SrcColor(MetaSystem.Graphics.queryInt("SRC_COLOR")),
        OneMinusSrcColor(MetaSystem.Graphics.queryInt("ONE_MINUS_SRC_COLOR")),
        DstColor(MetaSystem.Graphics.queryInt("DST_COLOR")),
        OneMinusDstColor(MetaSystem.Graphics.queryInt("ONE_MINUS_DST_COLOR")),
        SrcAlpha(MetaSystem.Graphics.queryInt("SRC_ALPHA")),
        OneMinusSrcAlpha(MetaSystem.Graphics.queryInt("ONE_MINUS_SRC_ALPHA")),
        DstAlpha(MetaSystem.Graphics.queryInt("DST_ALPHA")),
        OneMinusDstAlpha(MetaSystem.Graphics.queryInt("ONE_MINUS_DST_ALPHA")),
        ConstantColor(MetaSystem.Graphics.queryInt("CONSTANT_COLOR")),
        OneMinusConstantColor(MetaSystem.Graphics.queryInt("ONE_MINUS_CONSTANT_COLOR")),
        ConstantAlpha(MetaSystem.Graphics.queryInt("CONSTANT_ALPHA")),
        OneMinusConstantAlpha(MetaSystem.Graphics.queryInt("ONE_MINUS_CONSTANT_ALPHA"));
        private final int value;

        Factor(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public enum Equation {
        FuncAdd(MetaSystem.Graphics.queryInt("FUNC_ADD")),
        FuncSubtract(MetaSystem.Graphics.queryInt("FUNC_SUBTRACT")),
        FuncReverseSubtract(MetaSystem.Graphics.queryInt("FUNC_REVERSE_SUBTRACT")),
        Min(MetaSystem.Graphics.queryInt("MIN")),
        Max(MetaSystem.Graphics.queryInt("MAX"));
        private final int value;

        Equation(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public abstract Blend func(Factor src, Factor dst);
}
