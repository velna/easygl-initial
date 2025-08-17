package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.Color;
import com.vanix.easygl.commons.util.IntEnumCache;
import com.vanix.easygl.core.graphics.Blending;
import com.vanix.easygl.core.graphics.Graphics;

public class GlBlending extends GlFeature<Blending> implements Blending {
    private static final IntEnumCache<Function> FunctionCache = new IntEnumCache<>(Function.class, 2, 0xf);
    private static final IntEnumCache<Equation> EquationCache = new IntEnumCache<>(Equation.class, 0xf);

    public GlBlending(Graphics graphics) {
        super(GLX.GL_BLEND, graphics);
    }

    public Blending function(Function src, Function dst) {
        GLX.glBlendFunc(src.value(), dst.value());
        return this;
    }

    @Override
    public Blending function(Function srcRgb, Function dstRgb, Function srcAlpha, Function dstAlpha) {
        GLX.glBlendFuncSeparate(srcRgb.value(), dstRgb.value(), srcAlpha.value(), dstAlpha.value());
        return this;
    }

    @Override
    public Function functionSrcRGB() {
        return FunctionCache.valueOf(GLX.glGetInteger(GLX.GL_BLEND_SRC_RGB));
    }

    @Override
    public Function functionDstRGB() {
        return FunctionCache.valueOf(GLX.glGetInteger(GLX.GL_BLEND_DST_RGB));
    }

    @Override
    public Function functionSrcAlpha() {
        return FunctionCache.valueOf(GLX.glGetInteger(GLX.GL_BLEND_SRC_ALPHA));
    }

    @Override
    public Function functionDstAlpha() {
        return FunctionCache.valueOf(GLX.glGetInteger(GLX.GL_BLEND_DST_ALPHA));
    }

    @Override
    public Blending color(float red, float green, float blue, float alpha) {
        GLX.glBlendColor(red, green, blue, alpha);
        return this;
    }

    @Override
    public Color color() {
        float[] data = new float[4];
        GLX.glGetFloatv(GLX.GL_BLEND_COLOR, data);
        return new Color(data[0], data[1], data[2], data[3]);
    }

    @Override
    public Blending equation(Equation equation) {
        GLX.glBlendEquation(equation.value());
        return this;
    }

    @Override
    public Blending equation(Equation equationRgb, Equation equationAlpha) {
        GLX.glBlendEquationSeparate(equationRgb.value(), equationAlpha.value());
        return this;
    }

    @Override
    public Equation equationRGB() {
        return EquationCache.valueOf(GLX.glGetInteger(GLX.GL_BLEND_EQUATION_RGB));
    }

    @Override
    public Equation equationAlpha() {
        return EquationCache.valueOf(GLX.glGetInteger(GLX.GL_BLEND_EQUATION_ALPHA));
    }
}