package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class Blend implements Feature<Blend> {

    public enum Factor {
        Zero("ZERO"),
        One("ONE"),
        SrcColor("SRC_COLOR"),
        OneMinusSrcColor("ONE_MINUS_SRC_COLOR"),
        DstColor("DST_COLOR"),
        OneMinusDstColor("ONE_MINUS_DST_COLOR"),
        SrcAlpha("SRC_ALPHA"),
        OneMinusSrcAlpha("ONE_MINUS_SRC_ALPHA"),
        DstAlpha("DST_ALPHA"),
        OneMinusDstAlpha("ONE_MINUS_DST_ALPHA"),
        ConstantColor("CONSTANT_COLOR"),
        OneMinusConstantColor("ONE_MINUS_CONSTANT_COLOR"),
        ConstantAlpha("CONSTANT_ALPHA"),
        OneMinusConstantAlpha("ONE_MINUS_CONSTANT_ALPHA");
        private final int value;

        Factor(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    public enum Equation {
        FuncAdd("FUNC_ADD"),
        FuncSubtract("FUNC_SUBTRACT"),
        FuncReverseSubtract("FUNC_REVERSE_SUBTRACT"),
        Min("MIN"),
        Max("MAX");
        private final int value;

        Equation(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        public int value() {
            return value;
        }
    }

    public abstract Blend func(Factor src, Factor dst);
}
