package com.vanix.easygl.opengl;

import com.vanix.easygl.commons.SimpleIntEnum;
import com.vanix.easygl.commons.primitives.Rectanglef;
import com.vanix.easygl.core.graphics.Viewport;
import org.lwjgl.system.MemoryStack;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public class GlViewport extends SimpleIntEnum implements Viewport {

    public GlViewport(int value) {
        super(value);
    }

    @Override
    public Viewport setDepthRange(double near, double far) {
        GLX.glDepthRangeIndexed(value, near, far);
        return this;
    }

    @Override
    public Viewport setDepthRange(double[] values) {
        GLX.glDepthRangeArrayv(value, values);
        return this;
    }

    @Override
    public Viewport setDepthRange(DoubleBuffer values) {
        GLX.glDepthRangeArrayv(value, values);
        return this;
    }

    @Override
    public Viewport set(float x, float y, float width, float height) {
        GLX.glViewportIndexedf(value, x, y, width, height);
        GLX.checkError();
        return this;
    }

    @Override
    public Viewport set(float[] values) {
        GLX.glViewportArrayv(value, values);
        GLX.checkError();
        return this;
    }

    @Override
    public Viewport set(FloatBuffer values) {
        GLX.glViewportArrayv(value, values);
        GLX.checkError();
        return this;
    }

    @Override
    public Rectanglef<?> get() {
        try (MemoryStack stack = MemoryStack.stackGet()) {
            FloatBuffer buffer = stack.mallocFloat(4);
            GLX.glGetFloati_v(GLX.GL_VIEWPORT, value, buffer);
            return Rectanglef.of().set(buffer);
        }
    }

    @Override
    public float[] get(float[] values) {
        GLX.glGetFloati_v(GLX.GL_VIEWPORT, value, values);
        return values;
    }

    @Override
    public FloatBuffer get(FloatBuffer values) {
        GLX.glGetFloati_v(GLX.GL_VIEWPORT, value, values);
        return values;
    }
}
