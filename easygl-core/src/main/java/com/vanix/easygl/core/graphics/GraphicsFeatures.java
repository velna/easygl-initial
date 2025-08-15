package com.vanix.easygl.core.graphics;

import com.vanix.easygl.commons.EnumType;
import com.vanix.easygl.core.Feature;
import com.vanix.easygl.core.meta.MetaSystem;

public abstract class GraphicsFeatures extends EnumType<Feature> {
    public final Blend blend = of(Blend.class);
    public final CullFace cullFace = of(CullFace.class);
    public final DepthTest depthTest = of(DepthTest.class);
//    public final Feature<?>
    protected GraphicsFeatures() {
        super(Feature.class);
    }

    private static <T> T of(Class<T> type) {
        return MetaSystem.Window.of(type);
    }
}
