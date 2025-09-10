package com.vanix.easygl.core.graphics.feature;

import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.graphics.Version;

@Support(since = Version.GL31)
public interface PrimitiveRestart extends GraphicsFeature<PrimitiveRestart> {
    PrimitiveRestart setRestartIndex(int index);
}
