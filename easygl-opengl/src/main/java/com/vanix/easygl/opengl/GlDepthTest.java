package com.vanix.easygl.opengl;

import com.vanix.easygl.core.graphics.DepthTest;
import com.vanix.easygl.core.graphics.Graphics;

public class GlDepthTest extends GlFeature<DepthTest> implements DepthTest {
    public GlDepthTest(Graphics graphics) {
        super(GLX.GL_DEPTH_TEST, graphics);
    }

    public DepthTest function(Function function) {
        GLX.glDepthFunc(function.value());
        return this;
    }

    @Override
    public DepthTest range(double near, double far) {
        GLX.glDepthRange(near, far);
        return this;
    }

    @Override
    public DepthTest range(float near, float far) {
        GLX.glDepthRangef(near, far);
        return this;
    }

    @Override
    public float[] rangeFloat() {
        var data = new float[2];
        GLX.glGetFloatv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public double[] rangeDouble() {
        var data = new double[2];
        GLX.glGetDoublev(GLX.GL_DEPTH_RANGE, data);
        return data;
    }

    @Override
    public int[] rangeIntMapped() {
        var data = new int[2];
        GLX.glGetIntegerv(GLX.GL_DEPTH_RANGE, data);
        return data;
    }
}