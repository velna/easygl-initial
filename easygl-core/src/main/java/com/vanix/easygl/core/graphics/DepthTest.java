package com.vanix.easygl.core.graphics;

import com.vanix.easygl.core.Feature;

public interface DepthTest extends Feature<DepthTest, Graphics> {

    DepthTest function(CompareFunction function);

    DepthTest range(double near, double far);

    DepthTest range(float near, float far);

    float[] rangeFloat();

    double[] rangeDouble();

    int[] rangeIntMapped();
}
