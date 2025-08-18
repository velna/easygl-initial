package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.Support;

public interface DepthTest extends Feature<DepthTest, Graphics> {

    DepthTest function(CompareFunction function);

    DepthTest range(double near, double far);

    @Support(since = Version.GL41)
    DepthTest range(float near, float far);

    float[] rangeFloat();

    double[] rangeDouble();

    int[] rangeIntMapped();
}
