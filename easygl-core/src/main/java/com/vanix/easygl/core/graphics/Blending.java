package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public interface Blending extends Feature<Blending, Graphics> {

    enum Function implements IntEnum {
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

        Function(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    enum Equation implements IntEnum {
        FuncAdd("FUNC_ADD"),
        FuncSubtract("FUNC_SUBTRACT"),
        FuncReverseSubtract("FUNC_REVERSE_SUBTRACT"),
        Min("MIN"),
        Max("MAX");
        private final int value;

        Equation(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    Blending function(Function src, Function dst);

    Blending function(Function srcRgb, Function dstRgb, Function srcAlpha, Function dstAlpha);

    Function functionSrcRGB();

    Function functionDstRGB();

    Function functionSrcAlpha();

    Function functionDstAlpha();

    default Function[] functions() {
        return new Function[]{functionSrcRGB(), functionDstRGB(), functionSrcAlpha(), functionDstAlpha()};
    }

    default Blending color(Color rgba) {
        return color(rgba.red(), rgba.green(), rgba.blue(), rgba.alpha());
    }

    Blending color(float red, float green, float blue, float alpha);

    Color color();

    Blending equation(Equation equation);

    Blending equation(Equation equationRgb, Equation equationAlpha);

    Equation equationRGB();

    Equation equationAlpha();
}
