package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.commons.primitives.Rectanglef;
import com.vanix.easygl.core.meta.MetaSystem;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Viewport extends IntEnum {
    int MaxViewports = MetaSystem.Graphics.queryInt("MAX_VIEWPORTS");

    Viewport setDepthRange(double near, double far);

    Viewport setDepthRange(double[] values);

    Viewport setDepthRange(DoubleBuffer values);

    Viewport set(float x, float y, float width, float height);

    Viewport set(float[] values);

    Viewport set(FloatBuffer values);

    Rectanglef<?> get();

    float[] get(float[] values);

    FloatBuffer get(FloatBuffer values);
}
