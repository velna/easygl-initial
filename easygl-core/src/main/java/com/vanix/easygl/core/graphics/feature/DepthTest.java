package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.core.graphics.CompareFunction;

public interface DepthTest extends GraphicsFeature<DepthTest> {

    DepthTest setFunction(CompareFunction function);
}
