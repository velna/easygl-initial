package com.vanix.easygl.opengl.feature;

import com.vanix.easygl.core.graphics.CompareFunction;
import com.vanix.easygl.core.graphics.feature.DepthTest;
import com.vanix.easygl.core.graphics.Graphics;
import com.vanix.easygl.opengl.GLX;

public class GlDepthTest extends GlFeature<DepthTest> implements DepthTest {
    public GlDepthTest(Graphics graphics) {
        super(GLX.GL_DEPTH_TEST, graphics);
    }

    public DepthTest setFunction(CompareFunction function) {
        GLX.glDepthFunc(function.value());
        return this;
    }

    @Override
    public DepthTest setRange(double near, double far) {
        GLX.glDepthRange(near, far);
        return this;
    }

    @Override
    public DepthTest setRange(float near, float far) {
        GLX.glDepthRangef(near, far);
        return this;
    }

    @Override
    public float[] getRangeFloat() {
        var data = new float[2];
        GLX.glGetFloatv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public double[] getRangeDouble() {
        var data = new double[2];
        GLX.glGetDoublev(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public int[] getRangeIntMapped() {
        var data = new int[2];
        GLX.glGetIntegerv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }
}