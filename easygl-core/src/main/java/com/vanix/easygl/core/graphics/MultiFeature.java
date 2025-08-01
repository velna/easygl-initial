package com.vanix.easygl.core.graphics;

public interface MultiFeature<F extends Enum<F>, T extends MultiFeature<F, T>> extends Feature<T> {
    T enable(F feature);
    T disable(F feature);
}
