package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.Support;

public interface DepthTest extends Feature<DepthTest, Graphics> {

    DepthTest setFunction(CompareFunction function);

    DepthTest setRange(double near, double far);

    @Support(since = Version.GL41)
    DepthTest setRange(float near, float far);

    @Support(since = Version.GL41)
    float[] getRangeFloat();

    double[] getRangeDouble();

    int[] getRangeIntMapped();
}
