package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.CompareFunction;
import com.vanix.easygl.core.graphics.Version;

public interface DepthTest extends GraphicsFeature<DepthTest> {

    DepthTest setFunction(CompareFunction function);

    DepthTest setRange(double near, double far);

    @Support(since = Version.GL41)
    DepthTest setRange(float near, float far);

    @Support(since = Version.GL41)
    float[] getRangeFloat();

    double[] getRangeDouble();

    int[] getRangeIntMapped();
}
